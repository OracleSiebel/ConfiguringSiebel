//Register an asynchronous business component method. 
var inputArgs = {};
var oconsts = SiebelApp.Offlineconstants;
var childBCArrObjs = [];
inputArgs[oconsts.get("DOUIREG_OBJ_NAME")] = "Pharma Call Entry Mobile";
inputArgs[oconsts.get("DOUIREG_OBJ_TYPE")] = oconsts.get("DOUIREG_OBJ_TYPEAPPLET");
inputArgs[oconsts.get("DOUIREG_OBJ_MTHD")] = "CanInvokeMethod";
inputArgs[oconsts.get("DOUIREG_SRVC_NAME")] = "pharmacallsvc";
inputArgs[oconsts.get("DOUIREG_SRVC_MTDH")] = "CanInvokeMethod";
inputArgs[oconsts.get("DOUIREG_EXT_TYPE")] = oconsts.get("DOUIREG_EXT_TYPEPRE");
SiebelApp.S_App.GetModel().ServiceRegistry(inputArgs);
inputArgs[oconsts.get("DOUIREG_OBJ_NAME")] = "Pharma Call Entry Mobile";
inputArgs[oconsts.get("DOUIREG_OBJ_TYPE")] = oconsts.get("DOUIREG_OBJ_TYPEAPPLET");
inputArgs[oconsts.get("DOUIREG_OBJ_MTHD")] = "InvokeMethod";
inputArgs[oconsts.get("DOUIREG_SRVC_NAME")] = "pharmacallsvc";
inputArgs[oconsts.get("DOUIREG_SRVC_MTDH")] = "InvokeMethod";
inputArgs[oconsts.get("DOUIREG_EXT_TYPE")] = oconsts.get("DOUIREG_EXT_TYPEPRE");
SiebelApp.S_App.GetModel().ServiceRegistry(inputArgs);
//Add the pharmacallsvc method to the pharmacallsvc business service
SiebelApp.pharmacallsvc = (function () {
  function pharmacallsvc(pm) {
  }
  SiebelJS.Extend(pharmacallsvc, SiebelApp.ServiceModel);
//Specify the logic for your asynchronous method. 
//pharmacallsvc.prototype.CanInvokeMethod = function (psInputArgs) {
  var psOutArgs = SiebelApp.S_App.NewPropertySet();
  var svcMthdName = "";
  var pBusComp = this.GetContext().BusComp();
  svcMthdName = psInputArgs.GetProperty("MethodName").toString();
  if (svcMthdName === "Submit") {
    pBusComp.GetFieldValue("Call Status");
    $.callback(this, function (retObj) {
      var callStatus = retObj.retVal;
      if(callStatus!== "Submitted"){
        psOutArgs.SetProperty("Invoked", true);
        psOutArgs.SetProperty("RetVal", true);
        $.setReturnValue({err: false, retVal: psOutArgs});
      }
      else{
        psOutArgs.SetProperty("Invoked", true);
        psOutArgs.SetProperty("RetVal", false);
        $.setReturnValue({err: false, retVal: psOutArgs});
      }
    });
  }
};
//Configure Siebel Open UI to run InvokeMethod on the business service if the svcMthdName variable contains a value of Submit.
pharmacallsvc.prototype.InvokeMethod = function (psInputArgs) {
  var svcMthdName = "";
  var psOutArgs = SiebelApp.S_App.NewPropertySet();
  svcMthdName = psInputArgs.GetProperty("MethodName").toString();
  if (svcMthdName === "Submit") {
    this.Submit();
    $.callback(this, function (retObj) {
      psOutArgs.SetProperty("Invoked", true);
      $.setReturnValue({err: false, retVal: psOutArgs});
    });
  }
};
//Define the method that includes your customization logic. This code defines the Submit method. It sets the value for the Status field to Submitted. 
pharmacallsvc.prototype.Submit = function () {
  var model= SiebelApp.S_App.GetModel();
  var pBusObj = model.GetBusObject("boName");
  var pBusComp = pBusObj.GetBusComp("bcName");
  var now = new Date();
  var strStatusField = pBusComp.GetUserProperty("Status Field");
  var pickName =     SiebelApp.S_App.GetActiveView().GetActiveApplet().GetControl("Status").
    GetPickApplet();
    pBusComp.SetFieldValue(strStatusField, "submit", true);
  $.callback(this, function (retObj) {
    pBusComp.WriteRecord();
    $.callback(this, function (retObj) {
    });
  });
}
