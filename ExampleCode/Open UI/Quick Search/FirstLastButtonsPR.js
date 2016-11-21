if (typeof(SiebelAppFacade.FirstLastButtonsPR) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.FirstLastButtonsPR");
	define("siebel/custom/FirstLastButtonsPR", ["siebel/jqgridrenderer"],
		function () {
		SiebelAppFacade.FirstLastButtonsPR = (function () {

			function FirstLastButtonsPR(pm) {
				SiebelAppFacade.FirstLastButtonsPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(FirstLastButtonsPR, SiebelAppFacade.JQGridRenderer);

			FirstLastButtonsPR.prototype.ShowUI = function () {
				SiebelAppFacade.FirstLastButtonsPR.superclass.ShowUI.apply(this, arguments);
				var appletId = this.GetPM().Get("GetPlaceholder");
				debugger;
				$("#pager_" + appletId + "_center tr")
				.prepend("<td id='top_pager'" + appletId + " class='ui-pg-button ui-corner-all' style='cursor: default;'><span class='ui-icon ui-icon-seek-top'></span></td>")
				.append("<td id='bottom_pager'" + appletId + " class='ui-pg-button ui-corner-all' style='cursor: default;'><span class='ui-icon ui-icon-seek-bottom'></span></td>")
			}

			FirstLastButtonsPR.prototype.BindEvents = function () {
				SiebelAppFacade.FirstLastButtonsPR.superclass.BindEvents.apply(this, arguments);
				var appletId = this.GetPM().Get("GetPlaceholder");
				var that=this;
				$("#pager_"+appletId)
					.find(".ui-icon-seek-top").hover(
						function () {
							$(this).parent().toggleClass("ui-state-hover");
						}
					)
					.on("click", function() {
						that.GetPM().ExecuteMethod("InvokeMethod", "GotoFirstSet");
					})
					.end()
					.find(".ui-icon-seek-bottom").hover(
						function () {
							$(this).parent().toggleClass("ui-state-hover");
						}
					)
					.on("click", function() {
						that.GetPM().ExecuteMethod("InvokeMethod", "GotoLastSet");
					});
			}

			return FirstLastButtonsPR;
		}
			());
		return "SiebelAppFacade.FirstLastButtonsPR";
	})
}