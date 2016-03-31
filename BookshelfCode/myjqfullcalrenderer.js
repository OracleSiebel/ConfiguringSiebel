if (typeof (SiebelAppFacade.MyJQFullCalRenderer) === "undefined") {   
    SiebelJS.Namespace('SiebelAppFacade.MyJQFullCalRenderer');
    define("siebel/custom/myjqfullcalrenderer",
      ["order!3rdParty/fullcalendar/fullcalendar",
        "order!3rdParty/jquery-ui/current/ui/jquery.ui.tooltip",
        "order!siebel/phyrenderer",
        "order!siebel/jqfullcalrenderer"], function() { 
        SiebelAppFacade.MyJQFullCalRenderer = (function() {
            function MyJQFullCalRenderer(proxy) {
              SiebelAppFacade.MyJQFullCalRenderer.superclass
                .constructor.call(this, proxy);
            }
            SiebelJS.Extend(MyJQFullCalRenderer,
              SiebelAppFacade.JQFullCalRenderer);
          MyJQFullCalRenderer.prototype.ShowUI = function() {
          SiebelAppFacade.MyJQFullCalRenderer.superclass
            .ShowUI.call(this);
          var pm = this.GetPM();
          var jqCalCtrl = this.GetCal();
          var ehMap = this.GetEventHandlerMap();
          var abuttonIcons = {
            prev: 'circle-triangle-e',
            next: 'circle-triangle-w'
          };
          var op = {
              allDaySlot: false,
              allDayText: '',
              axisFormat: 'h(:mm) TT',
              buttonText: {
                today: pm.Get("UISTR Today Button"),
                month: pm.Get("UISTR Monthly Tab"),
                week:  pm.Get("UISTR Weekly Tab"),
                day:   pm.Get("UISTR Daily Tab"),
                prev: '',
                next: '',
                prevYear: '',
                nextYear: ''
              },
              buttonIcons: abuttonIcons,
              columnFormat: {
                month: 'dddd',
                week: '',
                day: ''
              },
              contentHeight: 500,
              customWeek: pm.Get("Custom Week"),
              dayClick: function () {
                ehMap.OnDayClick.apply(ehMap, arguments); },
              dayNames: pm.Get("WeekDayStringArray"),
              defaultView: this.GetUtilityMap()
                .ViewModeToName(pm.Get("ViewMode")),
              droppable: pm.Get("enableDragAndDropInList"),
              drop: function(date, allDay, evt, ui, view) {
                evt.preventDefault();
                pm.OnControlEvent("externalDrop", date,
                  $.data(this).appletName, $.data(this).rowId);
                jqCalCtrl.fullCalendar('refetchEvents');
              },
              editable: pm.Get("CanUpdate"),
              eventAfterRender: function () {
                ehMap.OnEventAfterRender.apply(ehMap,
                arguments); },
              eventClick: function () {
                ehMap.OnEventClick.apply(ehMap, arguments); },
              eventDrop: function () {
                ehMap.OnEventDrop.apply(ehMap, arguments); },
              eventRender: function () {
                ehMap.OnEventRender.apply(ehMap, arguments); },
              eventResize: function () {
                ehMap.OnEventResize.apply(ehMap, arguments); },
              events: function () {
                ehMap.OnEventRefresh.apply(ehMap, arguments); },
              firstDay: { 
                'month': pm.Get("Week Day Number"),
                'agendaWeek': pm.Get("Week Day Number")
              },
              firstHour: pm.Get("Preferred Business Day Start Time")
                .substr(0, 2)-1,
              header: {
                left: 'prev title next',
                center: '',
                right: ''
              },
              hiddenDays: pm.Get("Preferred Weekly Mode") === 
                "7 Day Weekly" ? [] : pm.Get("Hidden Days"),
              isRTL: pm.Get("RTL Mode") === "Y",
              lazyFetching: false,
              longDateFormat: pm.Get("Long Date Format"),
              monthNames: pm.Get("MonthStringArray"), 
              resizable: true,
              select: function () { ehMap.OnCalSelect.apply(ehMap,
                arguments); },
              selectable: true,
              selectHelper: true,
              slotMinutes: parseInt(pm.Get(
                "Preferred Timeslot Interval"), 10),
              timeFormat: { 
                agenda: '',
                  '': 'h(:mm)t'
              },
              titleFormat: { 
                month: 'MMMM yyyy',
                week:  "<'&#8212;'>",
                day: 'MMMM d'
              },
              viewDisplay: function () { 
                ehMap.OnViewDisplayChange.apply(ehMap, arguments); }, 
              weekends: {
                'month': pm.Get("Preferred Weekly Mode") ===
                  "7 Day Weekly",
                'agendaWeek': pm.Get("Preferred Weekly Mode") ===
                  "7 Day Weekly",
                'agendaDay': true },
                  weekMode: 'liquid'
            };
            jqCalCtrl.fullCalendar("destroy");
            jqCalCtrl.fullCalendar(op);
            var altTxtToday = pm.Get("UISTR Go to Today") ||
              "Go to Today";
            var altTxtNxtday;
            var altTxtPrevday;
            var tmpNxtButton;
            var tmpPrevButton;
            if (pm.Get("RTL Mode") !== "Y") {
              altTxtNxtday = pm.Get("UISTR Next Day") ||
                "Next Day";
              altTxtPrevday = pm.Get("UISTR Previous Day") ||
                "Previous Day";
            } else {
              altTxtPrevday = pm.Get("UISTR Next Day") ||
                "Next Day";
              altTxtNxtday = pm.Get("UISTR Previous Day") ||
                "Previous Day";
              tmpNxtButton = $(".fc-button-next");
              tmpPrevButton = $(".fc-button-prev");
              tmpNxtButton.removeClass("fc-button-next");
              tmpNxtButton.addClass("fc-button-prev");
              tmpPrevButton.removeClass("fc-button-prev");
              tmpPrevButton.addClass("fc-button-next");
            }
            $(".fc-button-today").attr("title",altTxtToday);
            $(".fc-button-next").attr("title",altTxtNxtday);
            $(".fc-button-prev").attr("title",altTxtPrevday);
            this.GetCalCtrlBuilder().AddControls();
            pm.AddProperty("MultViewFrames",
              $('#_swecontent').find("[id^='_svf']")
                .filter(function(){
                  return this.clientHeight !== 0; }).length);
            this.resize();
            };
      return MyJQFullCalRenderer;
    }());
  return "SiebelAppFacade.MyJQFullCalRenderer";
  });
}
