if (typeof (SiebelAppFacade.PartialRefreshPM) === "undefined") {
    SiebelJS.Namespace("SiebelAppFacade.PartialRefreshPM");
    // Module with its dependencies
    define("siebel/samples/partialrefreshpm", ["siebel/pmodel"], function () {
        SiebelAppFacade.PartialRefreshPM = (function () {
            function PartialRefreshPM(proxy) {
                SiebelAppFacade.PartialRefreshPM.superclass.constructor.call(this, proxy);
            }

            SiebelJS.Extend(PartialRefreshPM, SiebelAppFacade.PresentationModel);

            PartialRefreshPM.prototype.Init = function () {
                SiebelAppFacade.PartialRefreshPM.superclass.Init.call(this);
                this.AddProperty("ShowJobTitleRelatedField", "");
                this.AddMethod("ShowSelection", SelectionChange, { sequence: false, scope: this });
                this.AddMethod("FieldChange", OnFieldChange, { sequence: false, scope: this });
            };

            function SelectionChange() {
                var controls = this.Get("GetControls");
                var control = controls["JobTitle"];
                var value = this.ExecuteMethod("GetFieldValue", control);
                this.SetProperty("ShowJobTitleRelatedField", (value ? true : false));
            }

            function OnFieldChange(control, value) {
                if (control.GetName() === "JobTitle") {
                    this.SetProperty("ShowJobTitleRelatedField", (value ? true : false));
                }
            }

            return PartialRefreshPM;
        }());
        return "SiebelAppFacade.PartialRefreshPM";
    });
}
