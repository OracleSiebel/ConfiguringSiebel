if (typeof (SiebelAppFacade.PartialRefreshPR) === "undefined") {
    SiebelJS.Namespace("SiebelAppFacade.PartialRefreshPR");
    //Module with its dependencies
    define("siebel/samples/partialrefreshpr", ["siebel/phyrenderer"], function () {
        SiebelAppFacade.PartialRefreshPR = (function () {
            function PartialRefreshPR(pm) {
                SiebelAppFacade.PartialRefreshPR.superclass.constructor.call(this, pm);
            }

            SiebelJS.Extend(PartialRefreshPR, SiebelAppFacade.PhysicalRenderer);

            PartialRefreshPR.prototype.Init = function () {
                SiebelAppFacade.PartialRefreshPR.superclass.Init.call(this);
                this.AttachPMBinding("ShowJobTitleRelatedField", ModifyLayout);
            };

            function ModifyLayout() {
                var controls = this.GetPM().Get("GetControls");
                var canShow = this.GetPM().Get("ShowJobTitleRelatedField");
                var WorkPhoneNum = controls["WorkPhoneNum"];
                var FaxPhoneNum = controls["FaxPhoneNum"];
                if (canShow) {
                    $("div#WorkPhoneNum_Label").show();
                    $("[name='" + WorkPhoneNum.GetInputName() + "']").show();
                    $("div#FaxPhoneNum_Label").show();
                    $("[name='" + FaxPhoneNum.GetInputName() + "']").show();
                }
                else {
                    $("div#WorkPhoneNum_Label").hide();
                    $("[name='" + WorkPhoneNum.GetInputName() + "']").hide();
                    $("div#FaxPhoneNum_Label").hide();
                    $("[name='" + FaxPhoneNum.GetInputName() + "']").hide();
                }
            }

            return PartialRefreshPR;
        }());
        return "SiebelAppFacade.PartialRefreshPR";
    });
}
