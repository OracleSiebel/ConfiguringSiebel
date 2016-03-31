if( typeof( SiebelAppFacade.ClientCtrlPModel ) === "undefined" ){

    SiebelJS.Namespace( 'SiebelAppFacade.ClientCtrlPModel' );
     //Module with its dependencies
    define("siebel/samples/clientctrlpmodel", [], function () {
    SiebelAppFacade.ClientCtrlPModel = ( function(){
        
        var consts  = SiebelJS.Dependency( "SiebelApp.Constants" ); 
        
        /* *
         * Constructor Function - ClientCtrlPModel
         * 
         * Parameter - Be a good citizen. Pass All parameter to superclass. 
         * */
        function ClientCtrlPModel(proxy){

            var m_recordset = [];
            
            SiebelAppFacade.ClientCtrlPModel.superclass.constructor.call( this, proxy);
            
            this.AddMethod( "AddClientControl", null, { core : true } );    // add into method array
  
            this.GetClientRecordSet = function( ) {
               return m_recordset ;
            };
            
        }
        
        /* By default, any List Applet in OpenUI gets initialized with ListPresentationModel object.
         * We want to enhance on top of it and hence must extend from ListPresentationModel to keep
         * the core functionality.
         */
        SiebelJS.Extend( ClientCtrlPModel, SiebelAppFacade.ListPresentationModel );

        ClientCtrlPModel.prototype.Init = function(){

            SiebelAppFacade.ClientCtrlPModel.superclass.Init.call( this );

            /* Attach Post Handler for LeaveField */
            this.AddMethod( "LeaveField", PostLeaveField, { sequence : false, scope : this } );

            /* Attach Pre Handler for GetFormattedFieldValue */
            this.AddMethod("GetFormattedFieldValue", PreGetFormattedFieldValue, { sequence : true, scope : this } );

             /* Attach Handler for Delete Record Notification as well */
            this.AttachNotificationHandler( consts.get( "SWE_PROP_BC_NOTI_DELETE_RECORD" ), HandleDeleteNotification );
         
        };
        
        function PreGetFormattedFieldValue(control, bUseWS, recIndex, returnStructure){
            if (utils.IsEmpty(recIndex)){
                recIndex = this.Get("GetSelection");
            }
            
            if (recIndex >=0) {
               var clientObj = this.GetClientRecordSet();
                var recordSet=this.Get("GetRawRecordSet");
                var id = recordSet[recIndex]["Id"];
                var flag = false;
                var value;
                switch(control.GetName()){
                    case "TestDateTime":
                        value = "12/8/2013 11:41 AM";
                        flag = true;
                        break;
                    case "TestChkbox":
                        value = recordSet[recIndex]["Row Status"];
                        flag = true;
                        break;
                    case "TestEdit":
                        value = recordSet[recIndex]["Name"];
                        flag = true;
                        break;
                    case "TestCalc":
                    case "TestCombo":
                    case "TestPhone":
                        value = "";
                        flag = true;
                        break;

                }
                if (flag){
                    if( clientObj[id] && clientObj[id][control.GetName()] != undefined ){
                        value = clientObj[id][control.GetName()];
                    }
                    else if (clientObj[id]){
                        clientObj[id ][control.GetName()] = value;
                    }
                    else{
                        var recordclient = {};
                        recordclient[control.GetName()] = value;
                        clientObj[id] = recordclient;
                    }
                    returnStructure[ "CancelOperation" ] = true;
                    returnStructure[ "ReturnValue" ]     = value;
                }    
               
            }
        }
        
       
        function PostLeaveField( control, value, notLeave, returnStructure ){
               
                var clientObj = this.GetClientRecordSet();
                var currSel = this.Get( "GetSelection" );
                var recordSet=this.Get("GetRawRecordSet");
                var id = recordSet[currSel]["Id"];

                if (clientObj[id] && returnStructure["ReturnValue"] != null){
                switch(control.GetName()){
                    case "TestDateTime":
                    case "TestChkbox":
                    case "TestPhone":
                    case "TestCalc":
                    case "TestCombo":
                    case "TestEdit":
                        clientObj[id][control.GetName()] = returnStructure[ "ReturnValue" ] ; 
                        break;
                }
            }
        }

        
        function HandleDeleteNotification(propSet){
            var activeRow = propSet.GetProperty( consts.get( "SWE_PROP_BC_NOTI_ACTIVE_ROW" ) );
            if( activeRow  === this.Get( "GetSelection" ) ){
                var delObj = this.GetClientRecordSet();
                delObj[ activeRow ] = null;
            }
        }
       
       function getDropdownVal() {
          var pArrValues = [];
          for (var i = 1; i < 9; i++) {
             pArrValues.push("Static val " + i);
          } 
          return pArrValues;
       }

        ClientCtrlPModel.prototype.UpdateModel = function(psInfo){

            /// PS Attribute info for Edit box
            SiebelAppFacade.ClientCtrlPModel.superclass.UpdateModel.call( this, psInfo );
            var ctrlTxtInfo = SiebelAppFacade.PresentationModel.GetCtrlTemplate ("TestEdit", "Test Edit", consts.get( "SWE_CTRL_TEXTAREA" ), 1);
            
             /// PS Attribute info for Check box
             var ctrlChkboxInfo = SiebelAppFacade.PresentationModel.GetCtrlTemplate ("TestChkbox", "Test Checkbox", consts.get( "SWE_CTRL_CHECKBOX" ), 2);
             
             /// PS Attribute info for Date\time picker
             var ctrlDateTimeInfo = SiebelAppFacade.PresentationModel.GetCtrlTemplate ("TestDateTime", "Test DateTime", consts.get( "SWE_CTRL_DATE_TZ_PICK" ), 3 );
            
            /// PS Attribute info for Combo box
             var ctrlComboInfo = SiebelAppFacade.PresentationModel.GetCtrlTemplate ("TestCombo", "Test Drop Down", consts.get( "SWE_CTRL_COMBOBOX" ), 10 );
             var pArrVal = getDropdownVal();
             ctrlComboInfo.SetPropertyStr(consts.get("SWE_PROP_CLIENT_CTRL_PICK_VAL"), pArrVal);
            
            /// PS Attribute info for Calculator
             var ctrlCalcInfo = SiebelAppFacade.PresentationModel.GetCtrlTemplate ("TestCalc", "Test Calc", consts.get( "SWE_CTRL_CALC" ), 14);
           
            // set SWE_CTRL_CLIENTTYPE property to achieve the formating provided by bc field. as it is used for phone format (mentioned below) 
             var ctrlphInfo = SiebelAppFacade.PresentationModel.GetCtrlTemplate ("TestPhone", "Test Phone", consts.get( "SWE_CTRL_TEXT" ), 15);
             ctrlphInfo.SetPropertyStr(consts.get("SWE_CTRL_CLIENTTYPE"), "phone");
           
            // Add PS info to inject into proxy
            this.ExecuteMethod( "AddClientControl", ctrlTxtInfo );
            this.ExecuteMethod( "AddClientControl", ctrlChkboxInfo );
            this.ExecuteMethod( "AddClientControl", ctrlDateTimeInfo );
            this.ExecuteMethod( "AddClientControl", ctrlComboInfo );
            this.ExecuteMethod( "AddClientControl", ctrlCalcInfo );
            this.ExecuteMethod( "AddClientControl", ctrlphInfo ); 

        };
        
        return ClientCtrlPModel;
    } ());

        return "SiebelAppFacade.ClientCtrlPModel";
    });
}
