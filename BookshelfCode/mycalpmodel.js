if (typeof (SiebelAppFacade.MyCalPresentationModel) === "undefined") {
    SiebelJS.Namespace('SiebelAppFacade.MyCalPresentationModel');
    define("siebel/custom/mycalpmodel", ["order!siebel/calpmodel"],
    function() {
    SiebelAppFacade.MyCalPresentationModel = (function() {
      var siebConsts = SiebelJS.Dependency("SiebelApp.Constants"); 
      function MyCalPresentationModel(proxy) {
        SiebelAppFacade.MyCalPresentationModel.superclass
          .constructor.call(this, proxy);
    }
    SiebelJS.Extend(MyCalPresentationModel,
      SiebelAppFacade.CalPresentationModel);
      MyCalPresentationModel.prototype.Init = function() {
      SiebelAppFacade.MyCalPresentationModel.superclass 
        .Init.call(this); 
      this.AttachEventHandler("externalDrop", 
        function (date, appletName, rowId) { 
        onExternalDropEvent.call(this, date, appletName, rowId); 
      }); 
    };
    function onExternalDropEvent(date, appletName, rowId) { 
      var data = appletName + "|" + rowId; 
      var formattedDate = $.fullCalendar 
        .formatDate(date, "MM/dd/yyyy HH:mm:ss"); 
      var inputPS = CCFMiscUtil_CreatePropSet(); 
      inputPS.SetProperty("StartDate", formattedDate); 
      inputPS.SetProperty("Data", data);
      this.ExecuteMethod("InvokeMethod", "DragAndDrop", inputPS); 
      }
      return MyCalPresentationModel;
    }());
    return "SiebelAppFacade.MyCalPresentationModel";
  });
}  
