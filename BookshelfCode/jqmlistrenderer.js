if (typeof (SiebelAppFacade.JQMListRenderer) === "undefined") {
    SiebelJS.Namespace('SiebelAppFacade.JQMListRenderer');
    // dependencies
   define("siebel/custom/JQMListRenderer", [], function () {
       SiebelAppFacade.JQMListRenderer = (function() {
        var utils       = SiebelJS.Dependency( "SiebelApp.Utils" );
        var siebConsts  = SiebelJS.Dependency( "SiebelApp.Constants" );
        var btnControl = siebConsts.get("SWE_PST_BUTTON_CTRL");
        var linkControl = siebConsts.get("SWE_CTRL_LINK");
        var watermarkNR = SiebelApp.S_App.LocaleObject.GetLocalString("IDS_SWE_MOBILE_NO_RECORDS_TO_DISPLAY");
        var watermarkNRTC = SiebelApp.S_App.LocaleObject.GetLocalString("IDS_SWE_MOBILE_TAP_TO_CREATE");
        function JQMListRenderer(proxy) {
            SiebelAppFacade.JQMListRenderer.superclass.constructor.call( this, proxy );
            // private
            var m_jqmList;
            var m_jqmListId;
            var m_delControl;
            var m_delBtnStatus;
            var m_RowSel;
            var m_Offset;
            var m_bToggleFlag = false;
//            var m_jqmrefresh = false;
            this.GetJQMList = function(){
                return m_jqmList;
            };
            this.SetJQMList = function(jqmList){
                m_jqmList = jqmList;
            };
            this.GetJQMListId = function(){
                return m_jqmListId;
            };
            this.SetJQMListId = function(jqmListId){
                m_jqmListId = jqmListId;
            };
            this.GetRowCount = function(){
                return ( m_jqmListId.children().size() );
            };
            this.GetCurRowSel = function(){
                return  m_RowSel;
            };
            this.SetCurRowSel = function(RowId){
                m_RowSel = RowId;
            };
            this.GetDelControl = function(){
                return m_delControl;
            };
            this.SetDelControl = function(delControl){
                m_delControl = delControl;
            };
            this.GetDelBtnStatus = function(){
                return m_delBtnStatus;
            };
            this.SetDelBtnStatus = function(delBtnStatus){
                m_delBtnStatus = delBtnStatus;
            };
            this.GetToggleFlag = function(){
                return m_bToggleFlag;
            };
            this.SetToggleFlag = function(bToggleFlag){
                m_bToggleFlag = bToggleFlag;
            };
             //Map control
            var m_MapCtrl = new SiebelAppFacade.JQMMapCtrl( this );
            this.GetMapCtrl = function(){
                return m_MapCtrl;
            };
            // Location Services - Flexible configuration through applet user properties
            var m_mapFieldArray = null;
            this.GetMapFieldArray = function(){
                m_mapFieldArray = this.GetPM().Get( "GetMapFields" );
                return m_mapFieldArray;
            };
            var m_mapLocationArray = null;
            this.GetMapLocationArray = function(){
                return m_mapLocationArray;
            };
            this.SetMapLocationArray = function(mapLocationArray){
                m_mapLocationArray = mapLocationArray;
            };
            // Bug # 14283190 - Map control for BindEvent
            var m_mapControl;
            this.GetMapControl = function(){
                return m_mapControl;
            };
            this.SetMapControl = function(mapControl){
                m_mapControl = mapControl;
            };
            //Bind ToggleMobileLayout function to HandleRowSelect (row click)
            this.GetPM().AttachPMBinding(
                "HandleRowSelect",
                function(){
                    SiebelAppFacade.JQMLayout.ToggleMobileLayout( true );
                },
                {
                    when : function(){
                        //Enable toggle layout when row is clicked on list applet in two conditions:
                        //1. <div enabletoggle='true'> is defined under parent <div> of list applet <div>, i.e. parent <div id="_Parentapplet_List">
                        //2. <div enabletoggle='true'> is defined under the list applet <div>, i.e. applet <div id="s_S_A1_div">
                        return  $( "#" + this.Get( 'GetFullId' )).parent().children( "div[enabletoggle='true']" ).length === 1 ||
                                 $( "#s_" + this.Get( 'GetFullId' ) + "_div" ).children( "div[enabletoggle='true']" ).length === 1;
                    }
                }
            );
/*
            this.GetJQMRefresh = function(){
                return m_jqmrefresh;
            };

            this.SetJQMRefresh = function(jqmrefresh){
                m_jqmrefresh = jqmrefresh;
            };
*/
      }
        SiebelJS.Extend( JQMListRenderer, SiebelAppFacade.JQMScrollContainer);
        function drilldownFormatter(cellvalue, ctrlName, recordIndex, ph) {
            return "<a id = \"listcol_"+ph+"_"+recordIndex +"\" href=\"javascript:void(0);\" class=\"drilldown\" name=\"" + ctrlName + "\">" + cellvalue + "</a>";
        }
        JQMListRenderer.prototype.ShowUI = function( controlSet ) {
            SiebelAppFacade.JQMListRenderer.superclass.ShowUI.call( this);
            this.m_Offset = 0;
            var pm = this.GetPM();
            var ph = pm.Get( "GetPlaceholder" );
            var scrollviewcontainer = "scrollviewcontainer_" + ph;
            //this.EnableListScrolling(ph,true,false,true); // this function should be called once for the entire list applet
            //var scrollviewcontainer = this.GetScrollcontainer();
            $("#" + ph).html("<div id='" + scrollviewcontainer + "'></div>");
            this.SetJQMList($("#" + scrollviewcontainer));
            var listId = "jqml_" + ph;
            this.GetJQMList().html("<ul id='" + listId + "'></ul>");
            var parentContainer = $( "#s_" + pm.GetProxy().GetFullId() + "_div" );
            var parent = $("#" + scrollviewcontainer).closest(".ListAppletContainer");
            var id = parent.attr('id');
            if (id)
            {
                this.SetParContforheight(id);
            }
            this.SetJQMListId($("#" + listId));
            var JQMLisId = this.GetJQMListId();
            JQMLisId.attr({
                'data-role': 'listview',
                'data-inset': 'true'
            });
            JQMLisId.listview();
            this.MakecontainerScrollable(scrollviewcontainer,true,false,true);
            if (this.IsPopup())
            {
               var titleEle = "<div class='jqmpopup_title'><span> Popup </span></div>";
               $("[name=popup]").prepend(titleEle);
               $("td.AppletStylePopup" ).attr ("style", "max-width:" + $('.ui-simpledialog-container').css("width") + " !important");
               $("#" + listId ).attr ("style", "max-width:" + $('.ui-simpledialog-container').css("width") + " !important");
               $("#" + ph).attr ("style", "max-width:" + $('.ui-simpledialog-container').css("width") + " !important");
               var btnbar = $("[name=popup]").find("tr.AppletButtons");
               if (btnbar !== undefined){
                 $(btnbar).attr ("style", "max-width:" + $('.ui-simpledialog-container').css("width") + " !important");
               }
               var bttmbar = $("[name=popup]").find("tr.AppletBttm").parent().parent();
               if (bttmbar !== undefined){
                 $(bttmbar).attr ("style", "width:" + $('.ui-simpledialog-container').css("width") + " !important");
               }
               $("#" + ph).addClass("ui-popup-dimension");
               var activeApplet = SiebelApp.S_App.GetActiveView ().GetActiveApplet ();
               if (activeApplet !== undefined && activeApplet !== null)
               {
                  var Ctrl = activeApplet.GetActiveControl ();
                  if (Ctrl === undefined || Ctrl === null)
                  {
                     var parentApplet = activeApplet.GetParentApplet ();
                     if (parentApplet !== undefined && parentApplet !== null)
                     {
                        Ctrl = parentApplet.GetActiveControl ();
                        if (Ctrl === undefined || Ctrl === null) {
                          Ctrl = parentApplet.GetControl ( activeApplet.GetPopupControl() );
                        }
                     }
                  }

                  if (Ctrl !== undefined && Ctrl !== null) {
                    this.SetPopupType (Ctrl.GetPopupType ());
                  }
                  else {
                    this.SetPopupType ("Assoc");
                  }
               }
           }
           else
           {
             this.SetPopupType ("");
           }
            //this.CreateUIButtons();
           this.ShowSearch();
//           this.SetJQMRefresh (true);
        };
       JQMListRenderer.prototype.ShowSearch = function(){
            if (false === this.IsPopup()) {
               var placeHolder = this.GetPM().Get( "GetPlaceholder" );
               var columns = [];
               var listOfColumns = this.GetPM().Get( "ListOfColumns" );
               for(var i = 0, len = listOfColumns.length; i < len; i++ ){
                   columns.push(listOfColumns[i].control.GetDisplayName());
               }
               this.GetSearchCtrl().ShowUI(columns, placeHolder);
            }
        };

       function IconMapFormatter( cellvalue, control ) {
         var IconMap = SiebelApp.S_App.GetIconMap();
         var IconMapName = SiebelApp.S_App.LookupStringCache(control.GetIconMap());
         if(IconMapName)
         {
            var IconMapArray = IconMap[IconMapName];
            if(IconMapArray)
            {
                for(var i = 0; i< IconMapArray.length; i ++)
                {
                    if(IconMapArray[i].iconName === cellvalue)
                    {
                        return IconMapArray[i].iconImage + "<span style='display:none;'>" + cellvalue + "</span>";
                    }
                }
            }
         }
         return cellvalue;
       }
        JQMListRenderer.prototype.BindData = function(bRefresh){
            if( this.inProgress  ) { return false; }
            var JQMListId = this.GetJQMListId();
            var pm = this.GetPM();
            var scrollDir = null;
            var scrollAmount = 0;
            var IsScroll = 0;
            scrollDir = pm.Get("GetScrollDir");
            scrollAmount = pm.Get("GetScrollAmount");
            scrollAmount = parseInt(scrollAmount, 10);
            var recordLength = pm.Get( "GetRecordSet" ).length;
            var Scrolllength = recordLength;
            var startoffset = 0;
            if(scrollDir !== null && scrollAmount !== 0 && scrollAmount < recordLength)
            {
                IsScroll = true;
                if(scrollAmount < 0 )
                {
                  scrollAmount = -scrollAmount;
                }
                if(scrollDir)
                {
                   startoffset = recordLength -  scrollAmount;//this.m_Offset +
                   this.m_Offset = this.m_Offset + scrollAmount;
                }
                else
                {
                   startoffset = 0;
                   Scrolllength = scrollAmount;
                   this.m_Offset = this.m_Offset - scrollAmount;
                }
            }
            else if( bRefresh ){
              this.ClearData( );
              this.m_Offset = 0;
            }
            var recordSet = pm.Get( "GetRecordSet" );
            var columnInfo = pm.Get( "ListOfColumns" );
            var currBusComp = this.GetPM().GetProxy().GetBusComp();
            var ctrlName;
            var foundLink = false;
            var ph = pm.Get( "GetPlaceholder" );
            var listId = "jqml_" + ph + "_";
            var headIndex;
            var ColList = columnInfo.slice();
            var index = 0;
            var mapLocationArray = [];
            // Map applet user properties
            var mapFieldArray = this.GetMapFieldArray ();
            var fldName = null;
            //Remove no record watermark
            var NRId = listId + 'NR';
            if ( $('#' + NRId).length === 1 ){
                $('#' + NRId).remove();
            }
            //No record for the list applet
            if (recordLength < 1) {
                var bCanToggle = false;
                var orientation = jQuery.event.special.orientationchange.orientation();
                //Allow toggle via watermark if all following conditions are true
                //1. enabletoggle for the list applet is set to true in .swt
                //2. new record can be created in the current list applet (because of limitation, we're not checking the target applet)
                //3. there are hide divs tagged for current landscape/portrait mode
                if ( ( $( "#" + pm.GetProxy().GetFullId()).parent().children( "div[enabletoggle='true']" ).length === 1 ||
                       $( "#s_" + pm.GetProxy().GetFullId() + "_div" ).children( "div[enabletoggle='true']" ).length === 1 ) &&
                     ( pm.ExecuteMethod( "CanInvokeMethod", "NewRecord" ))) {

                    this.SetToggleFlag (true);
                    if ( $("div[" + orientation + "='Hide']").length > 0 ) {
                        bCanToggle = true;
                    }
                }
                //Show no record watermark
                var element = '<div id="' + NRId + '" class="jqmNoRecord">';
                if (bCanToggle) {
                    element = element.concat('<a href="#" data-role="link">' + watermarkNRTC + '</a></div>');
                }
                else {
                    element = element.concat('<span>' + watermarkNR + '</span></div>');
                }
                $("#scrollviewcontainer_" + ph).prepend(element);
                //Enable toggle via clicking the watermark
                if (bCanToggle) {
                    $('#' + NRId + ' a').bind("click.SiebelAppFacade.JQMListRenderer", {ctx: this}, function(event){
                        SiebelAppFacade.JQMLayout.ToggleMobileLayout( true );
                    });
                }
                //Apply the proper watermark whenever orientation is changed
                $('#' + NRId).bind("orientationchange.SiebelAppFacade.JQMListRenderer", {ctx: this}, function(event){
                    var element = $(this);
                    var childElement;
                    //No hide divs tagged for current landscape/portrait mode
                    if ( $("div[" + event.orientation + "='Hide']").length === 0) {
                        if (element.children("a").length === 1) {
                            element.children("a").remove();
                            childElement = "<span>" + watermarkNR + "</span>";
                            element.append(childElement);
                        }
                    }
                    else {
                        //Enable toggle via the watermark
                        if (element.children("span").length === 1 && event.data.ctx.GetToggleFlag()) {
                            element.children("span").remove();
                            childElement = '<a href="#" data-role="link">' + watermarkNRTC + '</a>';
                            element.append(childElement);
                            $('#' + element.attr( "id" ) + ' a').bind("click.SiebelAppFacade.JQMListRenderer", {ctx: this}, function(event){
                               SiebelAppFacade.JQMLayout.ToggleMobileLayout( true );
                            });
                        }
                    }
                });
            }
            // to find the drill down column, (1) if there is a drilldown column, it'll be used as Heading
            // (2) if there are no drilldown columns, the first column is used as Heading
            // assumption, there should at least one column on the list
            for (index = 0; index < columnInfo.length; index++) {
                if (index === 0){
                    ctrlName = columnInfo[index].name;
                    fldName = columnInfo[index].control.GetFieldName();
                    headIndex = index;
                }
                if(columnInfo[index].isLink){
                    ctrlName = columnInfo[index].name;
                    fldName = columnInfo[index].control.GetFieldName();
                    foundLink = true;
                    headIndex = index;
                    break;
                }
            }
            ColList.splice(headIndex, 1);
            for (index = 0; index < ColList.length; index++) {
                if (index > 2){
                    ColList.splice(index, ColList.length-index);
                    break;
                }
            }
            for (var recordIndex = startoffset; recordIndex < Scrolllength; recordIndex++) {
                var liElement;
                var Index = recordIndex + this.m_Offset;
                liElement = '<li id="' + listId + Index +'" data-role="list-content" data-jqml="true">';
                if (foundLink){
                    liElement = liElement.concat('<label class="ui-li-heading">' + drilldownFormatter(recordSet[recordIndex][ctrlName], ctrlName, recordIndex, ph) + "</label>");
                }
                else{
                    if(columnInfo[headIndex].control.GetIconMap())
                    {
                       var fVal = IconMapFormatter.call(this, recordSet[recordIndex][ctrlName], columnInfo[headIndex].control);
                       var fLabel = columnInfo[headIndex].control.GetDisplayName();
                       liElement = liElement.concat('<p  class="ui-li-heading"><span>' + fLabel + ": </span>" + fVal + "</p>");
                    }
                    else
                    {
                       liElement = liElement.concat('<label class="ui-li-heading">' + recordSet[recordIndex][ctrlName] + "</label>");
                    }
                }
                // Location service - Get field value of address related field specified in applet user properties
                if (mapFieldArray !== null) {
                    // Loop through map fields, get the field value of the selected record
                    for (var mapFldIndex = 0; mapFldIndex < mapFieldArray.length; mapFldIndex++) {
                        var fieldVal = recordSet[recordIndex][mapFieldArray[mapFldIndex]];
                        if (!utils.IsEmpty(fieldVal)){
                            mapLocationArray.push (fieldVal);
                        }
                    }
                }
                for (index = 0; index < ColList.length; index++) {
                    var fldLabel = ColList[index].control.GetDisplayName();
                    var fldVal = recordSet[recordIndex][ColList[index].name];
                    fldName = ColList[index].control.GetFieldName();
                    if (index === 0){
                        /* Move Style to CSS */
                        /* liElement = liElement.concat("<p class='ui-li-aside'><strong>" + fldLabel + "</strong>: " + fldVal + "</p>"); */
                        if( ColList[index].control.GetIconMap() )
                        {
                           fldVal = IconMapFormatter.call(this, fldVal, ColList[index].control);
                           liElement = liElement.concat("<p class='ui-li-aside'><span>" + fldLabel + ": </span>" + fldVal + "</p>");
                        }
                        else
                        {
                           liElement = liElement.concat("<p class='ui-li-aside'><span>" + fldLabel + ": </span>" + fldVal + "</p>");
                        }
                        //liElement = liElement.concat("<p class='ui-li-aside'><span>" + fldLabel + ": </span>" + fldVal + "</p>");
                    }
                    else{
                        /* Move Style to CSS */
                        /* liElement = liElement.concat("<p><strong>" + fldLabel + "</strong>: " + fldVal + "</p>"); */
                        if( ColList[index].control.GetIconMap() )
                        {
                           fldVal = IconMapFormatter.call(this, fldVal, ColList[index].control);
                           liElement = liElement.concat("<p><span>" + fldLabel + ": </span>" + fldVal + "</p>");
                        }
                        else
                        {
                           liElement = liElement.concat("<p><span>" + fldLabel + ": </span>" + fldVal + "</p>");
                        }
                        //liElement = liElement.concat("<p><span>" + fldLabel + ": </span>" + fldVal + "</p>");
                    }
                }
                liElement = liElement.concat("<p class='ui-li-desc-dummy'></p>");
                liElement = liElement.concat('</li>');
                var sel;
                if(scrollDir!== null && !scrollDir)
                {
                      sel = this.m_Offset + scrollAmount;// - recordIndex;
                      sel = listId + sel;
                     $(liElement).insertBefore('#'+ sel);
                    // JQMListId.prepend(liElement);
                }
                else {
                    JQMListId.append(liElement);
                }
            }
            // event binding for row click and swipe to delete,
            // can't move the code to BindEvents as BindEvents is called before BindData
            // swipe right (1) position on the row (2) Delete button (third party plugin)
            // disable the button based on CanUpdate of the record
            // can't combine three events in one as we don't want to touch third party plugin
            var delControl = this.GetDelControl();
            if (delControl !== undefined) {
                //This is a thrid party call hence could not convert it into Delegate
                // swipe to Delete button
                var Findelement = $('#jqml_'+ph+' li') ;
                if( Findelement.length > 0 ){
                    $('#jqml_'+ph+' li').swipeDelete({
                        btnLabel: delControl.GetDisplayName(),
                        click: function(e){
                            $(".menuitem").remove();
                            e.preventDefault();
                            var uiStatus = new SiebelApp.UIStatus();
                            uiStatus.Busy( {} );
                            setTimeout( function() {
                                var control = delControl;
                                var controlPS = control.GetMethodPropSet();
                                var inputPS = controlPS.Clone();
                                pm.OnControlEvent( siebConsts.get( "PHYEVENT_INVOKE_CONTROL"),  delControl.GetMethodName(), inputPS );
                                //Remove the Button from UI
                                var list_id = "jqml_" + pm.Get( "GetPlaceholder" );
                                var selector = "#" + list_id;
                                $(selector + " .aSwipeBtn").remove();
                                uiStatus.Free();
                                }, 0);
                            return false; // Important! Since button true event would do page refresh
                        }
                    });
                }
            }
            // Set map location values array
            this.SetMapLocationArray (mapLocationArray);
            JQMListId.listview('refresh');
            if(IsScroll)
            {
              //   JQMListId.listview('refresh');
                 for (recordIndex = 1; recordIndex <= scrollAmount; recordIndex++) {
                     if (scrollDir)
                     {
                        index = this.m_Offset - recordIndex;
                     }
                     else
                     {
                        index = this.m_Offset + recordIndex + recordLength - 1 ;
                     }
                     JQMListId.children().remove("#" + listId + index);//$("#" + listId + index).remove();
                 }
                 this.SetNewscrollpos(scrollDir , scrollAmount);
                 IsScroll = false;
             }
            if (this.IsPopup())
            {
              this.fixHeight ();
            }
        };
       JQMListRenderer.prototype.GetOffset = function(startrow,endrow)
       {
            startrow = startrow + this.m_Offset;
            endrow = endrow + this.m_Offset;
            var pm = this.GetPM();
            var ph = pm.Get( "GetPlaceholder" );
            var listId = "jqml_" + ph + "_";
            var firstrow = listId + startrow;
            var lastrow = listId + endrow;
            var off = $("#" + lastrow).offset().top - $("#" + firstrow).offset().top;
           // var off = off + $("#" + lastrow).height();
            return off;
       };
       JQMListRenderer.prototype.BindEvents = function( controlSet ) {
            SiebelAppFacade.JQMListRenderer.superclass.BindEvents.call( this, controlSet );
            $( "#" + this.GetPM().Get( "GetPlaceholder" ) ).delegate("a.drilldown", "click", {ctx : this}, function( event ){
                var uiStatus = new SiebelApp.UIStatus();
                uiStatus.Busy({
                    target: SiebelApp.S_App.GetTargetViewContainer(),
                    mask: true
                });
                var self = event.data.ctx;
                var name = $( this ).attr( "name" );
                var rowId = $( this ).parents( "li" ).eq(0).attr( "id" );
                var listId = event.data.ctx.GetJQMListId().attr( "id" )+ "_";
                rowId = parseInt(rowId.replace(listId,""), 10);
                rowId  = rowId - event.data.ctx.m_Offset;
                var rownum = parseInt(rowId, 10);
                //console.log( "for name " + name + " rowId " + rowId );
                setTimeout( function() {
                    if( rownum >= 0 ){
                        self.OnRowSelect( rownum );
                        self.GetPM().OnControlEvent( siebConsts.get( "PHYEVENT_DRILLDOWN" ), name, rownum );
                    }
                    uiStatus.Free( );
                }, 0);
                //debugger;
            });
            // Remove the Delete Button on row click
            var pm = this.GetPM();
            var ph = pm.Get( "GetPlaceholder" );
            var listId = "jqml_" + ph + "_";
            $('#jqml_'+ph).delegate("li", "click", { ctx : this }, function(e){
                $(".menuitem").remove();
                e.preventDefault();
                //console.log("jqml row click");
                $('div.ui-btn, .aSwipeBtn', $(this).parent('ul')).remove();
                var selectrow =  $(this).attr("id");
                selectrow  =  parseInt(selectrow.replace(listId,""), 10);
                selectrow  = selectrow - e.data.ctx.m_Offset;
                e.data.ctx.OnRowSelect(selectrow);
                SiebelJS.Log("jqml row click");
            });
            // disable Delete button based CanUpdate of the row
            $('#jqml_'+ph).delegate( "li", "swiperight", { ctx : this }, function(e){
               $(".menuitem").remove();
               e.preventDefault();
               var selectrow =  $(this).attr("id");
               selectrow  =  parseInt(selectrow.replace(listId,""), 10);
               selectrow  = selectrow - e.data.ctx.m_Offset;
               e.data.ctx.OnRowSelect(selectrow);
               if(e.data.ctx.GetDelBtnStatus() === false){
                  $(this).find('.aSwipeBtn').addClass('ui-disabled');
               }
               //console.log("jqml swiperight to disable Delete button");
            });
            // Bug # 14283190 - BindEvent for Map
            var mapControl = this.GetMapControl();
            if (mapControl !== undefined && mapControl !== null)
            {
                if (mapControl.GetMethodName() === "ShowMapLocations" && typeof (google) !== 'undefined'){
                    // Bind click event with ShowMap button Id
                    var mapCtrl = this.GetMapCtrl();
                    var mapLocationArray = [];
                    $( "#" + ( mapControl.GetInputName() + "_Ctrl" ) ).bind( "click", { ctx : this, ctrl : mapControl }, function( event ){
                        var uiStatus = new SiebelApp.UIStatus();
                        uiStatus.Busy( {} );
                        setTimeout( function() {
                            mapLocationArray = event.data.ctx.GetMapLocationArray();
                            mapCtrl.ShowMapLocations(mapLocationArray);
                            uiStatus.Free();
                        }, 0);
                        return false; // Important! Since button true event would do page refresh
                    });
                }
            }
        };
       JQMListRenderer.prototype.ShowSelection = function() {
            if( this.inProgress  ) { return false; }
            this.ClearSelection();
            var rowCount = this.GetRowCount();
            var selArray = this.GetPM().Get( "GetRowsSelectedArray" );
            for (var i = 0; i < rowCount && i < selArray.length; i++){
                if( selArray [ i ]){
                    this.setSelection( i , false );
                }
            }
            SiebelAppFacade.JQMListRenderer.superclass.ShowSelection.call( this);
        };
        JQMListRenderer.prototype.ClearData = function(){
            var list_id = "jqml_" + this.GetPM().Get( "GetPlaceholder" );
            var selector = "#" + list_id;
            //remove children <li> under parent node <ul>
            $(selector).empty();
        };
        JQMListRenderer.prototype.setSelection = function(index) {
            var list_id = "jqml_" + this.GetPM().Get( "GetPlaceholder" ) + "_";
            var index_offset = this.m_Offset + index;
            index_offset = parseInt(index_offset, 10);
            var RowId = list_id + index_offset;
            var selector = "#" + RowId;
            $(selector).addClass("hilight");
            this.SetCurRowSel(index);
        };
/*
        JQMListRenderer.prototype.SelectRow = function(index, bSelected) {
            if( this.inProgress ) { return false; }
            if (bSelected){
                this.setSelection(index-1);
            }
        };
*/
        JQMListRenderer.prototype.OnRowSelect = function( rowId )
        {
            var ctrlKey = false;
            var popupType = this.GetPopupType ();
            if (popupType === "Assoc")
            {
               var selArray = this.GetPM().Get( "GetRowsSelectedArray" );
               var selCount = 0;
               for (var i = 0; i < selArray.length; i++){
                   if (selArray [ i ]){
                     ctrlKey = true;
                     break;
                   }
               }
            }
            if (((popupType === "Assoc") || (parseInt( rowId, 10 ) !== this.GetCurRowSel())) &&
                this.GetPM().OnControlEvent( siebConsts.get( "PHYEVENT_SELECT_ROW" ), rowId, ctrlKey )){
                // Is this where restoration of previous row should happen?
            }
        };
        JQMListRenderer.prototype.ClearSelection = function (){
            var list_id = "jqml_" + this.GetPM().Get( "GetPlaceholder" );
            var selector = "#" + list_id;
            $(selector).children('.hilight').removeClass('hilight');
            $(selector + " .aSwipeBtn").remove(); //If the focus is moved , remove the delete button too
        };
        JQMListRenderer.prototype.ShowUIControl = function( control, htmlMarkup, title ){
            SiebelAppFacade.JQMListRenderer.superclass.ShowUIControl.call( this, control, htmlMarkup, title );
            var controlType = control.GetUIType();
            var controlMethod;
            var canInvoke;
            var controlId = control.GetInputName();
            // in list, we are only taking care of Delete button (which is needed for swipe to delete
            if(controlType === btnControl){
                controlMethod = control.GetMethodName();
                if (controlMethod === "DeleteRecord"){
                    canInvoke = this.GetPM( ).ExecuteMethod( "CanInvokeMethod", controlMethod );
                    this.SetDelControl(control);
                    this.SetDelBtnStatus(canInvoke);
                    $( "#"+controlId ).hide();
                }
                // Bug # 14283190 - Set Map control for BindEvent
                if (controlMethod === "ShowMapLocations"){
                    this.SetMapControl(control);
                }
            }
        };
/*
        JQMListRenderer.prototype.CreateUIButtons = function() {
            var pm = this.GetPM();
            var controls = pm.Get( "GetControls" );
//            $( "#s_" + rendererBridge.GetProxy().GetFullId() + "_div" ).find( "td.AppletTitle" ).addClass("ui-input-text" ).text(rendererBridge.GetCaption() );
//            $( "#s_" + rendererBridge.GetProxy().GetFullId() + "_div" ).find( "img" ).remove();
//            $( "#s_" + rendererBridge.GetProxy().GetFullId() + "_div" ).attr( "data-theme","b");
            for( var controlName in controls ){
                if( controls.hasOwnProperty( controlName ) ){
                var control = controls[controlName];
                var controlType = control.GetUIType();
                var iconMarkup = "";
//                var icon = " data-iconpos='notext' ";
                var icon = " data-iconpos='center' ";
                var customButton = false;
                var controlMethod;
                var canInvoke;
                var imageSrc;
                var button;
                if(controlType === btnControl){
                        controlMethod = control.GetMethodName();
                        var ctrlGetDisplayName = control.GetDisplayName();
                        canInvoke = this.GetPM( ).ExecuteMethod( "CanInvokeMethod", controlMethod );
                       // Enable specialized buttons along with New and Delete
                       // if (controlMethod === "NewRecord" || controlMethod === "DeleteRecord")
                       if( controlMethod === "ExecuteQuery"  || controlMethod === "NewQuery"    ||
                            controlMethod === "UndoQuery"    || controlMethod === "UndoRecord"  ||
                            controlMethod === "GetChart"     || controlMethod === "WriteRecord" ||
                            controlMethod === "ResetRecord"  || controlMethod === "ShowQueryAssistant" || controlMethod === "DeleteRecord" ) {
                            // TODO: Not Supported yet!
                        } else {
                            // Move Style to CSS
                            // var button = $("<button id='" + control.GetInputName() + "_Ctrl' data-inline='true' data-theme='b'>" + ctrlGetDisplayName + "</button>");
                            imageSrc = canInvoke? control.GetEnabledBmp(): control.GetDisabledBmp();
                            if (imageSrc){
                                $( "#" + control.GetInputName()).append(imageSrc).addClass( canInvoke ? "sweButtonImageEnabled" : "sweButtonImageDisabled" );
                                continue;
                            }
                            if (controlMethod === "DeleteRecord"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='delete'");
                            }
                            else if (controlMethod === "NewRecord" && control.GetMethodPropSet().GetProperty("SWESP") === "true"){
                                iconMarkup = iconMarkup.concat(icon);
                                iconMarkup = iconMarkup.concat("data-icon='check'");
                            }
                            else if (controlMethod === "NewRecord" || controlMethod === "CreateRecord"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='plus'");
                            }
                            else if (controlMethod === "WriteRecord"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='custom-save'");
                            }
                            else if (controlMethod === "PickRecord" || controlMethod === "AddRecord"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='check'");
                            }
                            else if (controlMethod === "CloseApplet"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='delete'");
                            }
                            else if (controlMethod === "EditRecord"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='custom-edit'");
                            }
                            else if (controlMethod === "ShowMapLocations"){
                               iconMarkup = iconMarkup.concat(icon);
                               iconMarkup = iconMarkup.concat("data-icon='small-marker'");
                            }
                            else if (controlMethod === "NxtDay"){
                               iconMarkup = iconMarkup.concat('data-iconpos="notext"');
                               iconMarkup = iconMarkup.concat("data-icon='arrow-r'");
                            }
                            else if (controlMethod === "PrevDay"){
                               iconMarkup = iconMarkup.concat('data-iconpos="notext"');
                               iconMarkup = iconMarkup.concat("data-icon='arrow-l'");
                            }
                            else
                            {
                                customButton = true;
                                iconMarkup = iconMarkup.concat(icon);
                                iconMarkup = iconMarkup.concat("data-icon='null'");
                            }
                            iconMarkup = iconMarkup.concat(" title='" + ctrlGetDisplayName + "'");
//                            var button = $("<button id='" + control.GetInputName() + "_Ctrl' data-inline='true'" + iconMarkup + ">" + ctrlGetDisplayName + "</button>");
                            if(!customButton)
                            {
                                button = "<button id='" + control.GetInputName() + "_Ctrl' data-inline='true'" + iconMarkup + "></button>";
                            }
                            else
                            {
                                button = "<button id='" + control.GetInputName() + "_Ctrl' data-inline='true'" + iconMarkup + ">" + ctrlGetDisplayName + "</button>";
                            }
                            $( "#"+control.GetInputName() ).append(button).trigger('create');
                            //HARANANT - For styling Calendar and the Map Buttons
                            if (controlMethod === "NxtDay"){
                               $( "#"+control.GetInputName() +"_Ctrl" ).parent().addClass('ui-btn-NxtDay');
                            }
                            else if (controlMethod === "PrevDay"){
                               $( "#"+control.GetInputName() +"_Ctrl" ).parent().addClass('ui-btn-PrevDay');
                            }
                            else if (controlMethod === "ShowMapLocations"){
                               $( "#"+control.GetInputName() +"_Ctrl" ).parent().addClass('ui-btn-ShowMap');
                            }
                            if(!canInvoke){
                                //enable map button
                                // Bug # 14232382 - Map funtions and control binding should be eliminated if Google API not loaded
                                if (controlMethod === "ShowMapLocations" && typeof (google) !== 'undefined') {
                                    $("#"+control.GetInputName()+"_Ctrl").button('enable');
                                    $("#"+control.GetInputName()+"_Ctrl").addClass("showMapLocations");
                                }
                                else
                                {
                                    $("#"+control.GetInputName()+"_Ctrl").button('disable');
                                    if (controlMethod === "DeleteRecord"){
                                        this.SetDelBtnStatus(canInvoke);
                                    }
                                }
                            }
                        }
                        if (controlMethod === "DeleteRecord"){
                            this.SetDelControl(control);
                            this.SetDelBtnStatus(canInvoke);
                        }
                }
                if (controlType === linkControl) {
                    controlMethod = control.GetMethodName();
                    canInvoke = this.GetPM( ).ExecuteMethod( "CanInvokeMethod", controlMethod );
                    imageSrc = canInvoke? control.GetEnabledBmp() : control.GetDisabledBmp();
                    if(imageSrc){
                        $("#"+control.GetInputName()).append( imageSrc);
                    }
                }

              }
            }
        };
*/
        JQMListRenderer.prototype.EnableControl = function( control, canUpdate ){
            SiebelAppFacade.JQMListRenderer.superclass.EnableControl.call( this, control, canUpdate );
            if( typeof( control.GetInputName ) === "function" ){
                if( control.GetUIType() === btnControl ){
                    if (control.GetMethodName() === "DeleteRecord"){
                        this.SetDelBtnStatus(canUpdate);
                    }
                    // enable map button while row selection
                    // Bug # 14232382 - Map funtions and control binding should be eliminated if Google API not loaded
                    if (control.GetMethodName() === "ShowMapLocations" && typeof (google) !== 'undefined') {
                        $("#"+control.GetInputName()+"_Ctrl").button('enable');
                        $("#"+control.GetInputName()+"_Ctrl").addClass("showMapLocations");
                    }
                }
            }
        };
        JQMListRenderer.prototype.SetCellEdit = function( bEnable ){
        };
        JQMListRenderer.prototype.SetCellValue = function( rowId,fieldName,newValue ){
        };
        return JQMListRenderer;
    }());
      return "SiebelAppFacade.JQMListRenderer";
  });
}
