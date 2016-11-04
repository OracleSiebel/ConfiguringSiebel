if (typeof(SiebelAppFacade.QuickSearchPR) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.QuickSearchPR");
	define("siebel/custom/QuickSearchPR", ["siebel/jqgridrenderer"],
		function () {
		SiebelAppFacade.QuickSearchPR = (function () {

			function QuickSearchPR(pm) {
				SiebelAppFacade.QuickSearchPR.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(QuickSearchPR, SiebelAppFacade.JQGridRenderer);

			QuickSearchPR.prototype.BindEvents = function () {
				SiebelAppFacade.QuickSearchPR.superclass.BindEvents.apply(this, arguments);
				/* Remap mousdown event to account for button being held down */
				var pr=this;
				var pm = pr.GetPM();
				var placeHolder = pm.Get("GetPlaceholder");
				var intervals = [];
				var intervalId;
				
				$("#next_pager_" + placeHolder +
					", #last_pager_" + placeHolder +
					", #prev_pager_" + placeHolder +
					", #first_pager_" + placeHolder)
				.children("span").each(function(){
					originalHandler = $._data($(this)[0]).events.click[0].handler;
					$(this)
						.unbind("click");
					$(this).parent()	
						.bind("mousedown", {ctx : pr}, function (evt) {
							var that=$(this).find("span")[0];
							originalHandler.call(that,evt);
							intervalId = setInterval(function () {
								originalHandler.call(that,evt)}, 100);
							intervals.push(intervalId);
						})
						.bind("mouseup mouseleave", function () {
							while (intervals.length > 0) {
								var interval = intervals.splice(0, 1);
								clearInterval(interval[0]);
							}
						})
				})
			}

			return QuickSearchPR;
		}
			());
		return "SiebelAppFacade.QuickSearchPR";
	})
}