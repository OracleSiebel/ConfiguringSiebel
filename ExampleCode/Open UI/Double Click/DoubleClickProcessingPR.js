//Regenerate using:http://duncanford.github.io/prpm-code-generator/?prpm=PR&object=DesktopList&name=DoubleClickProcessing&userprops=&comments=No&logging=No
if (typeof(SiebelAppFacade.DoubleClickProcessingPR) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.DoubleClickProcessingPR");
	define("siebel/custom/DoubleClickProcessingPR", ["siebel/jqgridrenderer"],
		function () {
		SiebelAppFacade.DoubleClickProcessingPR = (function () {

			function DoubleClickProcessingPR(pm) {
				SiebelAppFacade.DoubleClickProcessingPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(DoubleClickProcessingPR, SiebelAppFacade.JQGridRenderer);

			DoubleClickProcessingPR.prototype.BindEvents = function () {
				SiebelAppFacade.DoubleClickProcessingPR.superclass.BindEvents.apply(this, arguments);

				var pm = this.GetPM();
				
				var appletId = pm.Get("GetFullId");
				$("#" + appletId).find(".ui-jqgrid-view").dblclick(function () {
					if ($("#" + appletId).find(".AppletStylePopup .siebui-icon-pickrecord").length > 0)
						pm.ExecuteMethod("InvokeMethod", "PickRecord");
					else if ($("#" + appletId).find(".AppletStylePopup .siebui-icon-addrecord").length > 0)
						pm.ExecuteMethod("InvokeMethod", "AddRecord");
				});
				$("#" + appletId).find("#sieb-ui-popup-mvg-available").find(".ui-jqgrid-view").dblclick(function () {
					pm.ExecuteMethod("InvokeMethod", "AddRecord");
					//pm.ExecuteMethod("InvokeMethod", "ExecuteQuery"); //3.04.23: deactivated due Bug 36212976 : ALT+ENTER SHORTCUT KEYS NOT WORKING IN MULTI-TAB SCENARIO
				});
				$("#" + appletId).find("#sieb-ui-popup-mvg-selected").find(".ui-jqgrid-view").dblclick(function () {
					pm.ExecuteMethod("InvokeMethod", "DeleteRecords");
					//pm.ExecuteMethod("InvokeMethod", "ExecuteQuery");  //3.04.23: deactivated due Bug 36212976 : ALT+ENTER SHORTCUT KEYS NOT WORKING IN MULTI-TAB SCENARIO
				});
			}

			return DoubleClickProcessingPR;
		}
			());
		return "SiebelAppFacade.DoubleClickProcessingPR";
	})
}
