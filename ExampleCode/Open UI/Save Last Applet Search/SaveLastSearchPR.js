if (typeof(SiebelAppFacade.SaveLastSearchPR) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.SaveLastSearchPR");

	define("siebel/custom/SaveLastSearchPR", ["siebel/jqgridrenderer"],

		function () {
		SiebelAppFacade.SaveLastSearchPR = (function () {

			function SaveLastSearchPR(pm) {
				SiebelAppFacade.SaveLastSearchPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(SaveLastSearchPR, SiebelAppFacade.JQGridRenderer);
			
			SaveLastSearchPR.prototype.BindEvents = function () {
				SiebelAppFacade.SaveLastSearchPR.superclass.BindEvents.call ( this );
				
				var pm = this.GetPM();
				
				var appletId = pm.Get("GetFullId");
				var placeholder = pm.Get("GetPlaceholder");
				var that=this;

				// Find automation
				var find = $("#"+appletId).find("[aria-label=Find]")

				var downKeyEvent = $.Event("keydown");
				downKeyEvent.keyCode = $.ui.keyCode.DOWN;
				var enterKeyEvent = $.Event("keydown");
				enterKeyEvent.keyCode = $.ui.keyCode.ENTER;

				var pref = SiebelApp.S_App.NewPropertySet();
				var prefKey = "LastFindUsed";
				var prevFind = pm.Get(prefKey);

				$(find)
					.val(prevFind==undefined?" ":prevFind)
					/*.trigger(downKeyEvent)
					.trigger(enterKeyEvent)*/
				setTimeout(function(){$("#"+appletId).find("[aria-label='Starting with']").focus();},0);

				$(find).on("autocompletechange", function() {
					pref.SetProperty("Key",prefKey);
					pref.SetProperty(prefKey,$(this).val());
					
					pm.OnControlEvent(consts.get("PHYEVENT_INVOKE_CONTROL"),pm.Get(consts.get("SWE_MTHD_UPDATE_USER_PREF")),pref);
				})
			}

			return SaveLastSearchPR;
		}());
		return "SiebelAppFacade.SaveLastSearchPR";
	});
}
