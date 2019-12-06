if (typeof(SiebelAppFacade.ConstrainInputPW) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.ConstrainInputPW");
	define("siebel/custom/ConstrainInputPW", [],
		function () {
		SiebelAppFacade.ConstrainInputPW = (function () {

			function ConstrainInputPW(pm) {
				SiebelAppFacade.ConstrainInputPW.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(ConstrainInputPW, SiebelAppFacade.CalculatorPW);

			ConstrainInputPW.prototype.BindEvents = function () {
				SiebelAppFacade.ConstrainInputPW.superclass.BindEvents.apply(this, arguments);
				$(this.GetEl()).keypress(function (e) {
					var key = String.fromCharCode(e.which);
					var allowed = ",.0123456789";
					
					if (!allowed.includes(key))
						e.preventDefault();

				});
			}

			return ConstrainInputPW;
		}());

		SiebelApp.S_App.PluginBuilder.AttachPW(consts.get("SWE_CTRL_CALC"), SiebelAppFacade.ConstrainInputPW, function (control, objName) {
			return true;
		});

		return "SiebelAppFacade.ConstrainInputPW";
	})
}
