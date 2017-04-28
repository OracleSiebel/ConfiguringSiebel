if (typeof(SiebelAppFacade.ContactFormLinkedInPR) === "undefined") {
	SiebelJS.Namespace("SiebelAppFacade.ContactFormLinkedInPR");

	define("siebel/custom/ContactFormLinkedInPR", [], function () {
		SiebelAppFacade.ContactFormLinkedInPR = (function () {

			function ContactFormLinkedInPR(pm) {
				SiebelAppFacade.ContactFormLinkedInPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(ContactFormLinkedInPR, SiebelAppFacade.PhysicalRenderer);

			ContactFormLinkedInPR.prototype.ShowUI = function () {
				SiebelAppFacade.ContactFormLinkedInPR.superclass.ShowUI.apply(this, arguments);
				var controls = this.GetPM().Get("GetControls");
				htmlIcon = '<img name="myLinkedInIcon" src="images/custom/linkedin.png" height="16" width="16" style="float:right; cursor:pointer !important;">';
				$('#' + controls["LastName"].GetName() + '_Label').parent().append(htmlIcon);
			};

			ContactFormLinkedInPR.prototype.BindEvents = function () {
				SiebelAppFacade.ContactFormLinkedInPR.superclass.BindEvents.apply(this, arguments);
				var that=this;
				$('[name="myLinkedInIcon"]').click(function () {
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
			
			return ContactFormLinkedInPR;
		}());

		return "SiebelAppFacade.ContactFormLinkedInPR";
	});
}