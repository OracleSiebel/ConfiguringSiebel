//Initialised using using:http://duncanford.github.io/prpm-code-generator/?prpm=PR&object=DesktopList&name=ShowAllSearchResults&userprops=&comments=No&logging=No
if (typeof(SiebelAppFacade.ShowAllSearchResultsPR) === "undefined") {

 SiebelJS.Namespace("SiebelAppFacade.ShowAllSearchResultsPR");
 define("siebel/custom/ShowAllSearchResultsPR", ["siebel/searchpanerenderer"],
  function () {
   SiebelAppFacade.ShowAllSearchResultsPR = (function () {

    function ShowAllSearchResultsPR(pm) {
     SiebelAppFacade.ShowAllSearchResultsPR.superclass.constructor.apply(this, arguments);
    }

    SiebelJS.Extend(ShowAllSearchResultsPR, SiebelAppFacade.SearchPaneRenderer);

    ShowAllSearchResultsPR.prototype.BindData = function (bRefresh) {
     SiebelAppFacade.ShowAllSearchResultsPR.superclass.BindData.apply(this, arguments);
		console.log("Bind Data : "+bRefresh);
		if (bRefresh) {
			setTimeout(function() {
				$("#srchpanecontainer span.siebui-icon-arrowsm-right").each(function() {
					if (!$(this).parent().hasClass("siebui-search-pane-inline-open")) {
						$(this).click();
					}
				})
			},1000);
		}
    }

    return ShowAllSearchResultsPR;
   }()
  );
  return "SiebelAppFacade.ShowAllSearchResultsPR";
 })
}
