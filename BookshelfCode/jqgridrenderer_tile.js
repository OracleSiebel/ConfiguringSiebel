if ( typeof ( SiebelAppFacade.JQGridRenderer ) === "undefined" ) {
    SiebelJS.Namespace( 'SiebelAppFacade.JQGridRenderer' );
    SiebelAppFacade.JQGridRenderer = ( function() {
      // dependencies
      var utils       = SiebelJS.Dependency( "SiebelApp.Utils" );
      var siebConsts  = SiebelJS.Dependency( "SiebelApp.Constants" ); 
      var combo            = siebConsts.get( "SWE_CTRL_COMBOBOX" );
      var calc             = siebConsts.get( "SWE_CTRL_CALC" );
      var checkbox         = siebConsts.get( "SWE_CTRL_CHECKBOX" );
      var mailTo           = siebConsts.get( "SWE_CTRL_MAILTO" );
      var dateTimePick     = siebConsts.get( "SWE_CTRL_DATE_TIME_PICK" );
      var datePick         = siebConsts.get( "SWE_CTRL_DATE_PICK" );
      var dateTimezonePick = siebConsts.get( "SWE_CTRL_DATE_TZ_PICK" );
      var textArea         = siebConsts.get( "SWE_CTRL_TEXTAREA" );
      var mvg              = siebConsts.get( "SWE_CTRL_MVG" );
      var pick             = siebConsts.get( "SWE_CTRL_PICK" );
      var url              = siebConsts.get("SWE_CTRL_URL");
      function JQGridRenderer( proxy ) {
        SiebelAppFacade.JQGridRenderer.superclass.constructor.call( this, proxy );
        //private
        var m_jqGrid;
        var m_gridInfo;
        var m_colMap = new SiebelAppFacade.GridColumnHelper( );
        this.SetGridInfo = function( gridInfo ){
        	m_gridInfo = gridInfo;
          };
        this.GetGridInfo = function(){
            return m_gridInfo;
        };
        this.SetGrid = function( grid ){
          m_jqGrid = grid;
        };
        this.GetGrid = function(){
          return m_jqGrid;
        };
        this.GetRowCount = function(){
          return 10; //return ( this.GetGrid().getRowData().length );  
		}
        this.GetColCount = function(){
          return 10; //(  this.GetGrid().jqGrid( "getGridParam", "colNames" ).length ); 
        };
        this.GetColName = function(index){
          return 1; // (  this.GetGrid().jqGrid( "getGridParam", "colNames" )[index] ); 
        };
        this.GetColumnHelper = function(){
          return m_colMap;
        };
      };
      SiebelJS.Extend( JQGridRenderer, SiebelAppFacade.PhysicalRenderer );
      function MoveToPreviousRow( e ) {
        var key = e.charCode || e.keyCode;
        if( e.shiftKey && key === 9 ){
          var that = this;
          var iRow = Number( this.GetSelectedRow() ) - 1;
          var colModel = this.GetGrid().jqGrid( "getGridParam", "colModel" );
          for( var iCol = ( colModel.length - 1 ); iCol >= 0; iCol-- ){
            if( colModel[ iCol ].hidden === false ) break;
          }
          if( iRow >= 0 ){
            this.OnRowSelect( iRow  );
            setTimeout( function(){
              that.GetGrid().jqGrid( 'editCell', iRow, iCol , true );
            }, 10);
          }  
        }
      }
      function MoveToNextRow( e ){
        var key = e.charCode || e.keyCode;
        if( !e.shiftKey && key === 9 ){
          var that = this;
          var iRow = Number( this.GetSelectedRow() ) + 1;
          var colModel = this.GetGrid().jqGrid( "getGridParam", "colModel" );
          for( var iCol = 0; iCol < colModel.length; iCol++ ){
            if( colModel[ iCol ].hidden === false ) break;
          }
          if( iRow <= this.GetRowCount() ){
            this.OnRowSelect( iRow ); 
            setTimeout( function(){
              that.GetGrid().jqGrid( 'editCell', iRow, iCol, true);
            }, 10);
          }
        }
      }
      function drilldownFormatter( cellvalue, options, rowObject ) {
//	      return "<a href=\""+ this.GetRendererBridge().GetDrilldownURL( 
//                    options.name, options.rowId-1, rowObject["Id"]) + "\" " + "onClick=\"SiebelApp.S_App.Drilldown(event)\" class=\"drilldown\">" + cellvalue + "</a>";
          return "<a href=\"javascript:void(0);\" class=\"drilldown\" name=\"" + this.GetColumnHelper().GetActualControlName( options.colModel.name ) + "\">" + cellvalue + "</a>";
      };
      function urlFormatter(  cellvalue ) {
         cellvalue = cellvalue || "";
         var url = cellvalue;
         if( typeof( cellvalue ) === "string" ) {
            if( (url.indexOf( "http://" ) != 0) && 
               (url.indexOf( "https://" ) != 0)) {
                  url = "http://" + url;
            }
         }
         return "<a href='" + url + "' target='_blank' >" + cellvalue + "</a>";
      };     
      function PrepareColumnInfo( listOfColumns ) {
        var columnName  = [];
        var columnModel = [];
        var current = null;
        var that = this;
        for( var index = 0, length = listOfColumns.length; index < length; index++ )
        {
          current = {};
          //Add the Column with FieldName as the key
          current.name = this.GetColumnHelper().AddColumn( listOfColumns[index].name, listOfColumns[index].control);
          current.editable = true;
          switch( listOfColumns[index].controlType ){
            case combo:
              current.edittype = "select";
              current.editoptions = current.editoptions || {}; 
              current.editoptions.value = ( function( ctrl ) {
                                              return function(){
                                                return that.OnComboInit( ctrl );
                                              };
                                            }( listOfColumns[index].control ) ) ;
              break;
            case datePick:
              current.editoptions = {}; // ?? 
              current.editoptions.dataInit = function( element ) {
                SiebelAppFacade.ControlBuilder.DatePick({
                  target    : element,
                  className : 'applet-list-datetime',
                  beforeShow: function(){
                    return that.CanUpdateControl( null, $( element ).attr( "name" ) );
                  }
                });
              };
              break;
            case url:
               current.formatter = function( cellvalue ){
                  return urlFormatter.call( that, cellvalue ); 
               }; 
               current.unformat = function( cellvalue ){
                  return cellvalue;
               };
               break;
            case dateTimePick:                
            case dateTimezonePick:                
              current.editoptions = {}; // ?? 
              current.editoptions.dataInit = function( element ) {
                SiebelAppFacade.ControlBuilder.DateTimePick({
                  target    : element,
                  className : 'applet-list-date',
                  beforeShow: function(){
                    return that.CanUpdateControl( null, $( element ).attr( "name" ) );
                  }
                });                    
              };
              break;
            case calc:
              break;
            case mailTo:
              current.formatter = "email";
              break;
            case textArea:
              current.edittype = "textarea";
              var options = {};
              options.rows="2";
              options.cols="10";
              current.editoptions = options;
              break;
            case checkbox:
              current.edittype = "checkbox";
              var options = {};
              options.value="Y:N";
              current.editoptions = options;
              break;
            case mvg :
              current.editoptions = {}; 
              current.editoptions.dataInit = function( element ) {
                SiebelAppFacade.ControlBuilder.Mvg({
                  target    : element,
                  className : 'applet-list-mvg',
                  scope     : that, 
                  click     : function( ctrl ){
                                  this.GetPM().OnControlEvent( siebConsts.get( "PHYEVENT_INVOKE_MVG" ), ctrl );
                              },
                  control   : that.GetColumnControl( $( element ).attr( "name" ) )
                });   
              };
              break;
            case pick:
              current.editoptions = {}; 
              current.editoptions.dataInit = function( element ) {
                SiebelAppFacade.ControlBuilder.Pick({
                  target    : element,
                  className : 'applet-list-pick',
                  scope     : that, 
                  click     : function( ctrl ){
                                  this.GetPM().OnControlEvent( siebConsts.get( "PHYEVENT_INVOKE_PICK" ), ctrl );
                              },
                  control   : that.GetColumnControl( $( element ).attr( "name" ) )
                });   
              };              
              break;              
          }
          if( listOfColumns[index].isLink ){
            // Formatter needs to unformat while Cell Editing..
            current.formatter = function( cellvalue, options, rowObject ){
                  return drilldownFormatter.call( that, cellvalue, options, rowObject ); 
            };
            current.unformat = function( cellvalue, options, cell ){
              return cellvalue;
            };
          }
          
          if( index === 0 )
          {
            current.editoptions = current.editoptions || {};
            current.editoptions.dataEvents = [
            { 
                type  : 'keydown', 
                fn    : function( e ){ MoveToPreviousRow.call( that, e );  }
            }];
          }
          if( ( index + 1) === length )
          {
            current.editoptions = current.editoptions || {};
            current.editoptions.dataEvents = [
            { 
                type  : 'keydown', 
                fn    : function( e ){  MoveToNextRow.call( that, e  );   }
            }];            
          }
          //Accessibility : Give a unique ID to each cell so that we can refer the ID and append the accessibility attributes
          current.cellattr = function (rowId, tv, rawObject, cm, rdata) { var id = 'id=' + rowId + cm.name ; return id; };
          columnModel.push( current );
          //Push the DisplayName in the columnName of Grid. Please not that
          // we have FieldName in the columnModel 
          columnName.push( listOfColumns[index].control.GetDisplayName());
        }
        if( this.GetPM().Get( "HasHierarchy" ) ){
           columnModel[0].unformat = columnModel[0].unformat || function( cellvalue, options, cell ){
                  return cellvalue;
           };
           columnModel.push({search:false,hidden:true,sortable:false,resizable:false,hidedlg:true});
           columnName.push("Search");
        }
        return {
          "columnName" : columnName,
          "columnModel": columnModel
        };
      }
      JQGridRenderer.prototype.ShowUI = function( ) {
        SiebelAppFacade.JQGridRenderer.superclass.ShowUI.call( this );
        var pm = this.GetPM();
        var columnInfo = PrepareColumnInfo.call( this, pm.Get( "ListOfColumns" )  );
        this.SetGrid( $( "#" + pm.Get( "GetPlaceholder" ) ) );
        var jqGridCtrl = this.GetGrid();
        var colIndex=0;
        try {
        	  this.SetGridInfo ({
                  datatype      : "local",
                  colModel      : columnInfo.columnModel,
                  colNames      : columnInfo.columnName,
                  shrinkToFit   : true,
                  autowidth     : true,
                  pager         : '#pager_'+ pm.Get( "GetPlaceholder" ),
                  sortname      : 'name', 
                  viewrecords   : true,
                  sortorder     : "desc",
                  multiselect   : true,
                  hoverrows     : false,
                  height        : 240
               });
             //Accessibility :: Add the Applet Summary Attribute to the Grid Table
             var appletSummary = pm.Get( "GetAppletSummary" );
             if ( appletSummary !== ""){
                $( "#" + pm.Get( "GetPlaceholder" )  ).attr( "summary", appletSummary );
             }
             jqGridCtrl.addClass( "siebui-boxlist" );
             jqGridCtrl.html( "<div class='siebui-boxlist-items'></div><div class='siebui-boxlist-pager'></div>" );
             jqGridCtrl.find( ".siebui-boxlist-pager " )
                       .append( "<button class='appletButton' title='Previous Set Of Record'> << </button> ")
                       .append( "<button class='appletButton' title='Previous Record'> < </button> ")
                       .append( "<button class='appletButton' title='Next Record'> > </button> ")
                       .append( "<button class='appletButton' title='Next Set Of Record'> >> </button> ");
             //Add QTP info
            for(colIndex=0; colIndex<columnInfo.columnModel.length; colIndex++)
            {
               var target = $("#jqgh_" + pm.Get( "GetPlaceholder" ) + "_" + columnInfo.columnModel[colIndex].name);
               SiebelAppFacade.JQGridRenderer.superclass.InjectQTPInfo.call(this,target,this.GetColumnControl(columnInfo.columnModel[colIndex].name));
            }
            
            this.resize( );
         }
         catch (error) {
            alert(error); // We need a better logging mechanism... 
         }  
      };
      JQGridRenderer.prototype.ShowSearch = function(){
          var placeHolder = this.GetPM().Get( "GetPlaceholder" );
          var columns = [];
          var listOfColumns = this.GetPM().Get( "ListOfColumns" );
          for(var columnName in listOfColumns){
              columns.push(listOfColumns[columnName].control.GetDisplayName());
          }
          this.GetSearchCtrl().ShowUI(columns, placeHolder);
          /*var that = this;
          this.GetSearchCtrl().GetInputField().result(function(event, data, formatted){
              var mapFieldValue = {};
              mapFieldValue[that.GetSearchCtrl().GetSelectedField()] = data;
              that.Query(mapFieldValue);
          });*/
      };
      /* TODO: If addRowData is capable of taking multiple records, why aren't we pushing it. */
      JQGridRenderer.prototype.BindData = function( bRefresh ){ 
        if( this.inProgress  ) { return false; }
        if( bRefresh ){
            this.ClearData( );
        }
        var pm = this.GetPM();
        var listCols = pm.Get( "ListOfColumns" );
        var recordSet = pm.Get( "GetRecordSet" );
        var recordLength = recordSet.length;
        var items = this.GetGrid().find( ".siebui-boxlist-items" );
        items.html( "" );
        var caption= listCols[0].name;
        for( var i = 0; i < listCols.length; i++ ){
            if( listCols[i].isLink === true ){
                caption = listCols[i].name;
                break;
            }
        }
        var gridInfo = this.GetGridInfo();
        for ( var recordIndex = 0; recordIndex < recordLength; recordIndex++) {
            items.append( "<div class='siebui-boxlist-item siebui-boxlist-item" + recordIndex + "'><div class='siebui-boxlist-item-caption'><p>" + 
                    drilldownFormatter.call(this, recordSet[ recordIndex ][ caption ], { colModel : gridInfo.colModel[i]}, recordSet[ recordIndex ] ) +  
                    "</p></div></div>" );
       }
        this.PostBindData();
      };
      JQGridRenderer.prototype.PostBindData = function() {
        var placeHolder = this.GetPM().Get( "GetPlaceholder" );
        $( "#first_pager_" + placeHolder ).removeClass( "ui-state-disabled" );
        $( "#prev_pager_"  + placeHolder ).removeClass( "ui-state-disabled" );
        $( "#next_pager_"  + placeHolder ).removeClass( "ui-state-disabled" );
        $( "#last_pager_"  + placeHolder ).removeClass( "ui-state-disabled" );
      };
      JQGridRenderer.prototype.BindEvents = function( controlSet ) {
        SiebelAppFacade.JQGridRenderer.superclass.BindEvents.call( this, controlSet );
        var that = this;
        $( ".siebui-boxlist-item", this.GetGrid()).live( "click", { ctx : this }, function(event){
            event.data.ctx.OnRowSelect($(this).parent().find( ".siebui-boxlist-item" ).index( $(this) ), event.ctrlKey, event.shiftKey);
            return false;
        });
        $( ".siebui-boxlist-pager button", this.GetGrid()).bind( "click", { ctx : this }, function( event ){
            var btn = this;
            var uiStatus = new SiebelApp.UIStatus();
            uiStatus.Busy({});
            setTimeout( function() { 
              event.data.ctx.OnPagination( $( btn ).attr( "title" ) );
              uiStatus.Free();
            }, 0 );
            return false;
        });
      };
      JQGridRenderer.prototype.SetCellValue = function(rowId,fieldName,newValue){
      }; 
      JQGridRenderer.prototype.FocusFirstControl = function(){
      };
      JQGridRenderer.prototype.SetFocusToControl = function(name){
      };
      JQGridRenderer.prototype.SetCellEdit = function(bEnable){
      };
      JQGridRenderer.prototype.ShowSelection = function(index) {
        if( this.inProgress  ) { return false; }
        this.ClearSelection();
        var rowCount = this.GetRowCount();
        var selArray = this.GetPM().Get( "GetRowsSelectedArray" );
        for (var i = 0; i < rowCount && i < selArray.length; i++){
            if( selArray [ i ])
            this.SelectRow( i, true );
        }
        SiebelAppFacade.JQGridRenderer.superclass.ShowSelection.call( this);                 
      };
      JQGridRenderer.prototype.SelectRow = function(index, bSelected) {
        if( this.inProgress  ) { return false; }   
        if( bSelected ){
            this.GetGrid( ).find( ".siebui-boxlist-item" ).eq( index ).addClass( "siebui-boxlist-item-selected" );
        }
      };
      JQGridRenderer.prototype.ClearSelection = function (){
        if( this.inProgress  ) { return false; }      
        this.UpdateSelectedRow();
//        this.GetGrid().resetSelection();
		this.GetGrid( ).find( ".siebui-boxlist-item" ).removeClass( "siebui-boxlist-item-selected" );
      };
      JQGridRenderer.prototype.UpdateSelectedRow = function (){  
      };
      JQGridRenderer.prototype.ClearData = function(){
        if( this.inProgress  ) { return false; }      
          this.GetGrid().find( ".siebui-boxlist-items" ).html( "" );
      };
      /* Event Handling Methods... */
      JQGridRenderer.prototype.OnPagination = function( title ) {
        var direction = "";
        switch( title )
        {
          case "Next Record" : 
              direction =  "nxrc" ;
            break
          case "Next Set Of Record":
              direction =  "pgdn";
            break;
          case "Previous Record":
              direction =  "pvrc" ;
            break;
          case "Previous Set Of Record":
              direction =  "pgup";
            break;
        }
        if (! this.GetPM().OnControlEvent( siebConsts.get( "PHYEVENT_VSCROLL" ), direction ) ){
          // Error Handling ?
        }
      };
      JQGridRenderer.prototype.OnRowSelect = function( rowId, ctrlKey, shiftKey ) {
        //if (rowId != Number( this.GetGrid().jqGrid( "getGridParam", "selrow" ) ) && this.GetRendererBridge().OnControlEvent( ["sr", rowId - 1, ctrlKey, shiftKey] )){
        if( !ctrlKey && !shiftKey &&  
            Number(rowId) === Number( this.GetSelectedRow() ) &&
            this.GetSelectedRow( 'all' ).length === 1 ) 
        {
          return false;
        }
        this.UpdateSelectedRow();
        /*if (Number( this.GetSelectedRow( "all" ).length) === 1 
                  && Number(rowId) === Number( this.GetSelectedRow() ))
            return true;
          */
        if (!this.GetPM().OnControlEvent( siebConsts.get( "PHYEVENT_SELECT_ROW" ),  rowId, ctrlKey, shiftKey  )){
          // Is this where restoration of previous row should happen?
        }
      };
      JQGridRenderer.prototype.CommitPending = function(){
          this.UpdateSelectedRow();
      };
      JQGridRenderer.prototype.CanUpdateControl = function( rowid, cellname, value, iRow, iCol ) {
        return this.GetPM().ExecuteMethod( "CanUpdate",  this.GetColumnHelper( ).GetActualControlName(cellname) ); 
      };
      JQGridRenderer.prototype.GetColumnControl = function( cellname) {
        return this.GetColumnHelper().GetColumnControl(cellname); 
      };
      //TODO: Must be revisited.
      JQGridRenderer.prototype.OnComboInit = function( control ) {
        this.inProgress= true;
        var proxy = this.GetPM().GetProxy();
        proxy.SetActiveControl( control );
        if(utils.IsEmpty(SiebelApp.S_App.GetStaticPickValues(control.GetPickApplet())))
          SiebelApp.S_App.InvokeAppletMethod( proxy.GetName(), "EditPopup" );
        var ctrl = proxy.GetActiveControl();
        var arrvalues = SiebelApp.S_App.GetStaticPickValues(control.GetPickApplet()) || []; 
        var result = [];
        result.push( "" + ":" + "" );
        for( var index = 0 ; index < arrvalues.length; index++ )
        {
          result.push( arrvalues[index] + ":" + arrvalues[index] );
        }
        delete this.inProgress;
        
        return  result.join( ";" );
      };
      JQGridRenderer.prototype.resize = function( )
      {
      };
      JQGridRenderer.prototype.fixHeight = function( data ) {
      };
      JQGridRenderer.prototype.GetSelectedRow = function( mode ) {
      };
      return JQGridRenderer;
    }() );
    SiebelAppFacade.GridColumnHelper = function()
    {
      var colMap = {};
      var colControl = {};
      var colField = {};
      this.GetColMap = function() 
      {
        return colMap;
      };
      this.GetColControl = function() 
      {
        return colControl;
      };
      this.GetColField = function() 
      {
        return colField;
      };
    };
    SiebelAppFacade.GridColumnHelper.prototype = {
        AddColumn : function( orig, control )
        {
          var modified = null;
          if( typeof( orig ) === "string" )
          {
            modified = orig.replace( / /g, "_" );
            modified = modified.replace( /\//g, "_" );
            modified = modified.replace( /#/g, "_" );
            
            this.GetColMap( )[ modified ] = orig;
            this.GetColControl( )[ modified ] = control;
            this.GetColField( )[ control.GetFieldName() ] = orig;
          }
          return modified;
        },
        //This will return the Control Name
        GetActualControlName : function( col )
        {
             return this.GetColMap( )[ col ];
        },
        // This will return the Display Name/ Column Header in the List
        GetActualColumnName : function( col )
        {
            //Get the control's display Name
            return this.GetColControl( )[ col ].GetDisplayName()
        },
        GetModifiedColumnName : function( col )
        {
          var modified = null;
          if( typeof( col ) === "string" )
          {
            modified = col.replace( / /g, "_" );
            modified = modified.replace( /\//g, "_" );
            modified = modified.replace( /#/g, "_" );
          }
          return modified;
        },
        GetColumnControl : function( col )
        {
          return this.GetColControl( )[ col ];
        },
        GetColumnOfField : function( field )
        {
          return this.GetColField( )[ field ];
        },
        // TODO: Relook translate..
        TranslateObject : function(listOfColumns, bcFields )
        {
            var current = {};
            var colName = null;
            for(var i=0; i <listOfColumns.length;i++)
            {
                var fieldName = listOfColumns[i].control.GetFieldName();
                colName = listOfColumns[i].name;
                if(!SiebelApp.Utils.IsEmpty(colName)){
                  current[ colName.replace(/ /g, "_").replace( /\//g, "_" ).replace( /#/g, "_" ) ] = bcFields[ fieldName ];
                }
            }
                if( bcFields["Id"] )
                  current[ "Id" ] = bcFields[ "Id" ];
                return current;
            }
        };
}
