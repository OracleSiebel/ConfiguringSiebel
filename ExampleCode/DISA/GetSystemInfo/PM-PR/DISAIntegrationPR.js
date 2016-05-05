if (typeof(SiebelAppFacade.DISAIntegrationPR) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.DISAIntegrationPR");
	define("siebel/custom/DISAIntegrationPR", ["siebel/phyrenderer"],
		function () {
		SiebelAppFacade.DISAIntegrationPR = (function () {

			function DISAIntegrationPR(pm) {
				SiebelAppFacade.DISAIntegrationPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(DISAIntegrationPR, SiebelAppFacade.PhysicalRenderer);

			DISAIntegrationPR.prototype.Init = function () {
				SiebelAppFacade.DISAIntegrationPR.superclass.Init.apply(this, arguments);

				// attach to the PM method called by the WS when it responds
				this.AttachPMBinding("DISAResponse", ShowDISAResponse, {scope:this});
			}

			function ShowDISAResponse(response) {
				// add the DISA Response data to the UI
				lastField = $("#" + this.Get("GetFullId") + " td.scField:eq(-1)").parent().closest(".scField").parent();
				// Inline style is used here purely to minimise the example. The best practice would be to use a class and CSS.
				DISAContent = "<tr valign='top' style='color:red'><td class='scLabelRight' nowrap>IP Address:</td><td>" + response.HostAddress + "</td></tr>";
				DISAContent += "<tr valign='top' style='color:red'><td class='scLabelRight' nowrap>HostName:</td><td>" + response.HostName + "</td></tr>";
				DISAContent += "<tr valign='top' style='color:red'><td class='scLabelRight' nowrap>Processors:</td><td>" + response.Processor + "</td></tr>";
				DISAContent += "<tr valign='top' style='color:red'><td class='scLabelRight' nowrap>UserHome:</td><td>" + response.UserHome + "</td></tr>";
				lastField.after(DISAContent);
			}

			DISAIntegrationPR.prototype.ShowUI = function () {
				SiebelAppFacade.DISAIntegrationPR.superclass.ShowUI.apply(this, arguments);
				this.ExecuteMethod("callDISAPlugin");
			}

			return DISAIntegrationPR;
		}
			());
		return "SiebelAppFacade.DISAIntegrationPR";
	})
}