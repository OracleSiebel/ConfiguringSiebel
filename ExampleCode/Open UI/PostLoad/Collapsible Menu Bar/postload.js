if (typeof(SiebelAppFacade.Postload) == "undefined") {
	Namespace('SiebelAppFacade.Postload');
	(function() {
		SiebelApp.EventManager.addListner("postload", OnPostload, this);

		function OnPostload() {
			try {
				console.log("Loaded");

				if (!($("#myCollapse").length)) { // check if the element id is present
					// create the div id for the png and insert it after the ORACLE logo
					var collpaseHTML = "<div id='myCollapse' style='float: left;height: 40px!important;line-height: 40px;background-size:38px;background-image: url(images/custom/menu.png);background-repeat: no-repeat;width: 40px;'></div>";
					$(collpaseHTML).insertAfter(".siebui-logo");
					collapseHTML = null;
					$("#myCollapse").on("click", function() {

						var a = $("[title='Application Menu']");
						// on click we toggle the Application menu
						$(a).toggle('slow');
						if ($("#myCollapse").css("transform") == 'none') {
							$("#myCollapse").css({
								//when hide the menu  rotate the png
								"transform": "rotate(90deg)"
							});
						} else {
							$("#myCollapse").css({
								// reset the css transform when the menu is visible again
								"transform": ""
							});
						}
						a = null;

					});
				}

			} catch (error) {
				//No-Op
			}
		}

	}());
}