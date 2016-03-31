if( typeof( SiebelAppFacade.RecycleBinPModel ) === "undefined" ){

    SiebelJS.Namespace( "SiebelAppFacade.RecycleBinPModel" );
    
    define("siebel/samples/recyclebinpmodel", [], function () {
    
        SiebelAppFacade.RecycleBinPModel = ( function(){
            
            var consts  = SiebelJS.Dependency( "SiebelApp.Constants" ); 
            
            /* *
             * Constructor Function - RecycleBinPModel
             * 
             * Parameter - Be a good citizen. Pass All parameter to superclass. 
             * */
            function RecycleBinPModel(){
                SiebelAppFacade.RecycleBinPModel.superclass.constructor.apply( this, arguments );
            }
            
            /* By default, any List Applet in OpenUI gets initialized with ListPresentationModel object.
             * We want to enhance on top of it and hence must extend from ListPresentationModel to keep
             * the core functionality.
             */
            SiebelJS.Extend( RecycleBinPModel, SiebelAppFacade.ListPresentationModel );
    
            RecycleBinPModel.prototype.Init = function(){
                SiebelAppFacade.RecycleBinPModel.superclass.Init.call( this );
                
                /* Pre-Deletion Cache - Stores set of records (which are candidate for deletion,
                 * by virtue of being checked by our local lovely control) before requesting to server */
                this.AddProperty( "DeletionPendingSet",     [] );
                
                /* Deletion Cache - Actual Record Set which has been deleted */
                this.AddProperty( "DeletionCompleteSet",    [] );
                
                /* Index of Record which is requested by PhysicalRenderer to restore  */
                this.AddProperty( "restorationIndex",       -1 );
                
                this.AddProperty ("ObjectsUnderDeletion",   [] );
                
                /* Marker Function - Indicates staleness in PM which is to be consumed by 
                PhysicalRenderer to trigger UI refresh on subsequent data refresh  */ 
                this.AddMethod( "RefreshList", function(){} );
                
                /* PM's specific event which is being used to restore any record which was deleted and still present in local cache*/
                this.AttachEventHandler( "RESTORE", OnClickRestore );
                
                /* Attach Pre Handler for GetFormattedFieldValue */
                this.AddMethod("GetFormattedFieldValue", PreGetFormattedFieldValue, { sequence : true, scope : this } );
                
                /* Attach Pre Handler for LeaveField */
                this.AddMethod( "LeaveField", PreLeaveField, { sequence : true, scope : this } );
                
                /* Attach Handler for Delete Record Notification as well */
                this.AttachNotificationHandler( consts.get( "SWE_PROP_BC_NOTI_DELETE_RECORD" ), HandleDeleteNotification );
                
                /* Attach Pre Handler for InvokeMethod */
                this.AddMethod( "InvokeMethod", PreInvokeMethod, { sequence : true, scope : this } ); //pre handler
                
                /* Attach Handler when writerecord has completed at server-side and
                   client's core framework is yet to process the response */ 
                this.AttachPreProxyExecuteBinding( "WriteRecord", function(methodName, inputPS, outputPS){
                    // Empty Implementation as of now!
                });
    
                /* Attach Handler when writerecord processing has completed at server-side and
                client's core framework has also finished processing the response */ 
                this.AttachPostProxyExecuteBinding( "WriteRecord", PostWriteRecord);
            };
            
            //Creation of local control
            RecycleBinPModel.prototype.Setup = function(propSet){
                var mycontrol = SiebelApp.S_App.GetAppletControlInstance ( 
                    "Client_Select",
                    consts.get("SWE_CTRL_CHECKBOX"),
                    "Select",
                    "50" );
                this.Get("GetListOfColumns")["Client_Select"] = mycontrol;
                SiebelAppFacade.RecycleBinPModel.superclass.Setup.call( this, propSet );
            };
            
            /* OpenUI framework won't know anything about client side control which was injected in PM/PR.
             * Since OpenUI framework has very strict focus model and being unaware of client side control,
             * it should also ignore *any* query about it. For example, value for the control or managing enter/leave field
             * Function PreGetFormattedFieldValue determines if the field is custom client side field and indicates
             * core framework to ignore any further processing.
             */
            function PreGetFormattedFieldValue(control, bUseWS, recIndex, returnStructure){
                if (control.GetName() === "Client_Select"){
                    /* Cancel default operation if any. Also return value as per the API specs */
                    returnStructure[ "CancelOperation" ] = true;
                    returnStructure[ "ReturnValue" ]             = "";
                }
            }
            
            
            /* Like PreGetFormattedFieldValue, function PreLeaveField indicates core framework to do nothing
             * for client side control. Also it stores those values in PM properties as part of deletionpending items
             * When actual deletion happens, it uses these properties. 
             */
            function PreLeaveField( control, value, notLeave, returnStructure ){
                if (control.GetName() === "Client_Select"){
                    /* HANDLE WITH CARE! Should never be called unless it is client side custom control */
                    this.ExecuteMethod( "SetActiveControl", null );
                    
                    /* Our client side checkbox is being left. Let us handle changes here.
                       We also want the base functionality to not execute - since there isn't a BC field. */
                    var delObj = this.Get( "DeletionPendingSet" );
                    var currentSelection = this.Get( "GetSelection" );
                    if( value === "Y" ){
                        delObj[currentSelection] = this.Get("GetRecordSet")[currentSelection];
                    }
                    else{
                        delObj[currentSelection] = null;
                    }
                    /* Cancel Default Operation if any. Also indicate to caller that it has succeeded with return value */
                    returnStructure[ "CancelOperation" ] = true; 
                    returnStructure[ "ReturnValue" ] = true;
                }
            }
            
            /* PreInvokeMethod is being used to get handle of operation before delete/write record.
             * When in case of delete, if any (client side) checkbox control was selected, iterative delete is being invoked 
             * and the property InDeletion ensures that we are not running into infinite loop 
             * 
             * When restore is invoked, it will result in New Record + Write Record. Before writeRecord, we need to ensure that
             * BusinessModel gets populated correctly with local properties from our loved PM! 
             */
            function PreInvokeMethod( methodName, psInputArgs, lp, returnStructure){
                
                if( methodName === "DeleteRecord" && !this.Get( "InDeletion" ) ){
                    
                    /* InDeletion property is being used to avoid recursion. */
                    this.SetProperty( "InDeletion", true );
                    var deletionPending = this.Get( "DeletionPendingSet" );
                    if( deletionPending.length > 0 ){
                        for( var counter = deletionPending.length - 1; counter >= 0; counter-- ){
                            var currentObj = deletionPending[counter];
                            if( currentObj ){
                                /* Use with care! This below SetActiveControl is being done only for Client control. */
                                this.ExecuteMethod( "SetActiveControl", null );
                                /* Before Deleting, Server must know about activeRow and hence the following call */
                                this.ExecuteMethod( "HandleRowSelect", counter, false, false );
                                /* Aha, Let's check if we can delete? */
                                if( this.ExecuteMethod( "CanInvokeMethod", "DeleteRecord" ) ){
                                    /* Now we are going to request server to delete this record. Hence storing it in our Local PM.
                                       With last param to InvokeMethod being true, we are asking for Asynchronous Delete Operation */
                                    this.Get( "ObjectsUnderDeletion" )[ this.Get( "GetSelection" ) ] = currentObj;
                                    var inputPS = SiebelApp.S_App.NewPropertySet();
                                    this.ExecuteMethod ("InvokeMethod", "DeleteRecord", inputPS );
                                }
                            }
                        }
                        
                        /* We have processed all checked records and deleted whichever was possible. Hence resetting the property to empty Array again. */
                        this.SetProperty( "DeletionPendingSet", [] );
                        
                        /* Stop core framework processing for actual delete which was started on the actual selected record  */
                        returnStructure ["CancelOperation"] = true;
                        
                        /* Since we are doing a CancelOperation, we need to reset the busy state. */
                        SiebelApp.S_App.uiStatus.Free();
                    }
                    /* Reset the conditional flag property */
                    this.SetProperty("InDeletion", false);
                    
                }
                /* Trap WriteRecord only when it was initiated from our restore handler */
                else if( methodName === "WriteRecord" && this.Get("inRestoration") ){
                    var recordSet = this.Get( "DeletionCompleteSet" );
                    /* find the record which needs to be restored */
                    var record = recordSet[ this.Get( "restorationIndex" ) ];
                    var listOfColumns = this.Get( "ListOfColumns" );
                    var controls = this.Get( "GetControls" );
                    /* Iterate over the List of Column control and set(leaveField) pre-stored value (from the DeletionCompleteSet) for each*/
                    for( var i = 0, len = listOfColumns.length; i < len; i++ ){
                        var control = controls[ listOfColumns[ i ].name ];
                        if( control ){
                            this.ExecuteMethod( "LeaveField", control, record[control.GetFieldName()], true);
                        }
                    }
                }
            }
            
            
            /* If we have requested for record deletion in async manner (no matter if it was for single or multiple record),
             * we get notification for each deletion which has happened at the server correctly and accordingly, 
             * we manipulate local PM properties. Also indicates the PhysicalRenderer that now is the time to UpdateUI */
            function HandleDeleteNotification(propSet){
                var objectsUnderDeletion = this.Get( "ObjectsUnderDeletion" );
                if( objectsUnderDeletion.length > 0 ){
                    var activeRow = propSet.GetProperty( consts.get( "SWE_PROP_BC_NOTI_ACTIVE_ROW" ) );
                    if( activeRow  == this.Get( "GetSelection" ) && objectsUnderDeletion[ activeRow ] ){
                        this.Get("DeletionCompleteSet").push( objectsUnderDeletion[ activeRow ] );
                        objectsUnderDeletion[ activeRow ] = null;
                        this.ExecuteMethod( "RefreshList" );
                    }
                }
            }
            
            /* After WriteRecord handling at server (as well as client core framework), we need to determine if this  record 
             * was restored by our logic. If so, remove it from our local property set and indicate to PhysicalRenderer 
             * via "RefreshList" to do whatever is required in UI layer */
            function PostWriteRecord(methodName, inputPS, outputPS){
                if( this.Get( "inRestoration" ) ){
                    this.Get( "DeletionCompleteSet" )[ this.Get("restorationIndex") ] = null;
                    this.ExecuteMethod("RefreshList");
                    this.SetProperty("inRestoration", false);
                }
            }
            
            /* Example of how the physical event in UI should be handled in PM.
             * Handles whenever the restore button is clicked. Based on the index, fire NewRecord and WriteRecord to restore. */
            function OnClickRestore(index){
                /* Tough luck if Applet is read only!  */
                if( this.ExecuteMethod( "CanInvokeMethod", "NewRecord" ) ){
                    this.SetProperty( "inRestoration",      true );
                    this.SetProperty( "restorationIndex",   index );
                    
                    this.ExecuteMethod( "InvokeMethod", "NewRecord",    null, false );
                    this.ExecuteMethod( "InvokeMethod", "WriteRecord",  null, false );
                }
            }
    
            return RecycleBinPModel;
        } ());
        return "SiebelAppFacade.RecycleBinPModel";
    });
}