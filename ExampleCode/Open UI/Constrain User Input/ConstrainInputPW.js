if (typeof(SiebelAppFacade.ConstrainInputPW) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.ConstrainInputPW");
	define("siebel/custom/ConstrainInputPW", ["siebel/pwinfra"],
		function () {
		SiebelAppFacade.ConstrainInputPW = (function () {

			function ConstrainInputPW(pm) {
				SiebelAppFacade.ConstrainInputPW.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(ConstrainInputPW, SiebelAppFacade.CalculatorPW);

			ConstrainInputPW.prototype.BindEvents = function () {
				SiebelAppFacade.ConstrainInputPW.superclass.BindEvents.apply(this, arguments);
				$(this.GetEl()).keypress(function (e) {
					var keys = [44, 46];	//	comma, period

					for (i = 48; i < 58; i++)
						keys.push(i);	//	digits

					if (!(keys.includes(e.which)))
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
