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
					var a = [];
					var k = e.which;

					for (i = 48; i < 58; i++)
						a.push(i);
					a.push(46); //period

					if (!(a.indexOf(k) >= 0))
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