if (typeof(SiebelAppFacade.MyJQGridRenderer) === "undefined") {
    SiebelJS.Namespace('SiebelAppFacade.MyJQGridRenderer');
    define("siebel/custom/myjqgridrenderer", 
      ["order!3rdParty/jqGrid/current/js/i18n/grid.locale-en", 
      "order!3rdParty/jqGrid/current/js/jquery.jqGrid.min", 
      "order!3rdParty/jqgrid-ext", 
      "order!siebel/phyrenderer", 
      "order!siebel/jqgridrenderer"],
    function () {
          SiebelAppFacade.MyJQGridRenderer = (function() { 
          function   MyJQGridRenderer(pm) { 
            SiebelAppFacade.MyJQGridRenderer
              .superclass.constructor.call(this, pm); 
        }
        SiebelJS.Extend(MyJQGridRenderer,SiebelAppFacade.JQGridRenderer);
        MyJQGridRenderer.prototype.BindData = function(bRefresh){
          SiebelAppFacade.MyJQGridRenderer 
            .superclass.BindData.call(this, bRefresh);
            var pm = this.GetPM();
            var enableDragAndDropInList =
              pm.Get("enableDragAndDropInList");
            var that = this;
            if (enableDragAndDropInList) {
              $("#" + pm.Get("GetPlaceholder")).find("tr")
                  .on("dragstart", function(){
                $(this).data("origPosition",$(this).position());
                $(this).data("appletName",
                  pm.GetPMName().replace("_PM", ""));
                if ($(this).parent()
                  .find("tr.ui-state-highlight")
                  .not(".ui-draggable-dragging").length > 1){
                  $(this).data("rowId"
                    ,pm.Get("GetBusComp").
                      GetRecordSet()
                      [that.GetSelectedRow()-1].Id);
                  SiebelApp.S_App.GetPopupPM().
                    SetProperty("ismultislectmode", true);                         
                }
                else {
                  $(this).data("rowId",pm.Get("GetBusComp")
                    .GetRecordSet()[$(this).attr("id")-1].Id);     SiebelApp.S_App.GetPopupPM()
                  .SetProperty("ismultislectmode", false);                         
                }
              }); 
        }
      };
      return MyJQGridRenderer;
    }());
    return "SiebelAppFacade.MyJQGridRenderer";
  });
}
