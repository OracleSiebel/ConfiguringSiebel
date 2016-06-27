if (typeof(SiebelAppFacade.SortedViewTabDropDownPR) === "undefined") {
	SiebelJS.Namespace("SiebelAppFacade.SortedViewTabDropDownPR");

	define ("siebel/custom/SortedViewTabDropDownPR", ["order!siebel/accnavigationphyrender"], function () {

		SiebelAppFacade.SortedViewTabDropDownPR = (function () {

			function SortedViewTabDropDownPR(pm) {
				SiebelAppFacade.SortedViewTabDropDownPR.superclass.constructor.call(this, pm);
			}

			SiebelJS.Extend(SortedViewTabDropDownPR, SiebelAppFacade.AccNavigationPhyRenderer);
			
			SortedViewTabDropDownPR.prototype.BindData = function ( bRefresh ) {
				SiebelAppFacade.SortedViewTabDropDownPR.superclass.BindData.call ( this, bRefresh );
				$('#j_s_vctrl_div_tabScreen option').sort(NASort).appendTo('#j_s_vctrl_div_tabScreen');
				$('#j_s_sctrl_tabScreen option').sort(NASort).appendTo('#j_s_sctrl_tabScreen');				
				$('#j_s_sctrl_tabView option').sort(NASort).appendTo('#j_s_sctrl_tabView');				
			};

			function NASort(a, b) {    
				if (a.innerHTML == 'NA') {
					return 1;   
				}
				else if (b.innerHTML == 'NA') {
					return -1;   
				}       
				return (a.innerHTML > b.innerHTML) ? 1 : -1;
			};
			
			return SortedViewTabDropDownPR;
		}());
		
		return "SiebelAppFacade.SortedViewTabDropDownPR";
	})
}
