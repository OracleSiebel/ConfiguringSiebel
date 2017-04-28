if (typeof(SiebelAppFacade.ContactFormLinkedInATAPR) === "undefined") {
	SiebelJS.Namespace("SiebelAppFacade.ContactFormLinkedInATAPR");

	define("siebel/custom/ContactFormLinkedInATAPR", [], function () {
		SiebelAppFacade.ContactFormLinkedInATAPR = (function () {

			function ContactFormLinkedInATAPR(pm) {
				SiebelAppFacade.ContactFormLinkedInATAPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(ContactFormLinkedInATAPR, SiebelAppFacade.PhysicalRenderer);

			ContactFormLinkedInATAPR.prototype.ShowUI = function () {
				SiebelAppFacade.ContactFormLinkedInATAPR.superclass.ShowUI.apply(this, arguments);
				var controls = this.GetPM().Get("GetControls");

				htmlIcon = '<img name="myLinkedInIcon" src="images/custom/linkedin.png" height="16" width="16" style="float:right; cursor:pointer !important;">';
				$('#' + controls["LastName"].GetName() + '_Label').parent().append(htmlIcon);
				//basic ATA implementation
				autoOn = SiebelApp.S_App.IsAutoOn();
				if (autoOn) {
					var ATA={}; // add automation tag attributes
					ATA[consts.get("SWE_PROP_QTP_OT")] = 'Image';
					ATA[consts.get("SWE_PROP_QTP_RN")] = 'LinkedInIcon';
					ATA[consts.get("SWE_PROP_QTP_UN")] = 'CFLATAPR';
					$('[name="myLinkedInIcon"]').attr(ATA);
				}
			};

			ContactFormLinkedInATAPR.prototype.BindEvents = function () {
				SiebelAppFacade.ContactFormLinkedInATAPR.superclass.BindEvents.apply(this, arguments);
				var that=this;
				$('[name="myLinkedInIcon"]').click(function () {
					debugger;
					window.open(
						"http://www.linkedin.com/pub/dir/?first=" + GetFieldValue.call(that,"First Name") +
						"&last=" + GetFieldValue.call(that,"Last Name") + 
						"&search=Search",
						"width=500,height=400,toolbar=no,status=no"
					);
				});
			};

			function GetFieldValue(fieldName) {
				var oPM = this.GetPM();
				var controls = oPM.Get("GetControls");
				var fieldValue = null;
				$.each(controls,function(){if (this.GetFieldName()==fieldName) fieldValue = oPM.ExecuteMethod("GetFieldValue", this)});
				return fieldValue;
			}
			
			return ContactFormLinkedInATAPR;
		}());

		return "SiebelAppFacade.ContactFormLinkedInATAPR";
	});
}