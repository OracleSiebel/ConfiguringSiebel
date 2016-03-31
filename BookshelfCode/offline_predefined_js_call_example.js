//Do explicit registration
if (typeof (SiebelApp.pharmacallsvc) === "undefined") {
  SiebelJS.Namespace('SiebelApp.pharmacallsvc');
  var inputObj = {};
  var oconsts = SiebelApp.Offlineconstants;
  inputObj[oconsts.get("DOUIREG_OBJ_NAME")] = "Pharma Call Entry Mobile";
  inputObj[oconsts.get("DOUIREG_OBJ_TYPE")] = oconsts.get("DOUIREG_OBJ_TYPEAPPLET");
  inputObj[oconsts.get("DOUIREG_OBJ_MTHD")] = "DoCanInvokeMethod";
  inputObj[oconsts.get("DOUIREG_SRVC_NAME")] = "pharmacallsvc";
  inputObj[oconsts.get("DOUIREG_SRVC_MTDH")] = "DoCanInvokeMethod";
  inputObj[oconsts.get("DOUIREG_EXT_TYPE")] = oconsts.get("DOUIREG_EXT_TYPEOVERRIDE");
  SiebelApp.S_App.Model.ServiceRegistry(inputObj);
  inputObj[oconsts.get("DOUIREG_OBJ_NAME")] = "Pharma Call Entry Mobile";
  inputObj[oconsts.get("DOUIREG_OBJ_TYPE")] = oconsts.get("DOUIREG_OBJ_TYPEAPPLET");
  inputObj[oconsts.get("DOUIREG_OBJ_MTHD")] = "DoInvokeMethod";
  inputObj[oconsts.get("DOUIREG_SRVC_NAME")] = "pharmacallsvc";
  inputObj[oconsts.get("DOUIREG_SRVC_MTDH")] = "DoInvokeMethod";
  inputObj[oconsts.get("DOUIREG_EXT_TYPE")] = oconsts.get("DOUIREG_EXT_TYPEOVERRIDE");
  SiebelApp.S_App.Model.ServiceRegistry(inputObj);;
  SiebelApp.pharmacallsvc = (function () {
    function pharmacallsvc(pm) {
    }
    SiebelJS.Extend(pharmacallsvc, SiebelApp.ServiceModel); //Extending
    pharmacallsvc.prototype.DoInvokeMethod = function (psInputArgs) {
      var svcMthdName = "";
      var psOutArgs = CCFMiscUtil_CreatePropSet();
      //Get "MethodName" property from psInputArgs:
      svcMthdName = psInputArgs.GetProperty("MethodName").toString();
      if (svcMthdName === "Submit") {
         //Submit is a predefined function.
         this.Submit();
         $.callback(this, function (retObj) {
          if (!retObj.err) {
            //If DoInvokeMethod handlesthe Submit function call, then set the Invoked property to true:
            psOutArgs.SetProperty("Invoked", true);
            $.setReturnValue({ err: "", retVal: psOutArgs });
          }
          else {
            psOutArgs.SetProperty("Invoked", true);
            $.setReturnValue({ err: retObj.err, retVal: psOutArgs });
          }
        });
      }
     //The following code is required for all overridden DoInvokeMethod functions
      else {
        //If DoInvokeMethod does not handle the function call, then you must set the Invoked property to false:
        psOutArgs.SetProperty("Invoked", false);
        $.setReturnValue({ err: false, retVal: psOutArgs });
      }
      return(psOutArgs);
    };
    pharmacallsvc.prototype.DoCanInvokeMethod = function (psInputArgs) {
      var psOutArgs = CCFMiscUtil_CreatePropSet();
      var svcMthdName = "";
      svcMthdName = psInputArgs.GetProperty("MethodName").toString();
      if (svcMthdName === "Sign") {
        //If the current, overridden DoCanInvokeMethod function handles the function, then you must set Invoked property to true. Siebel Open UI will includes the return value in the RetVal property for the function from DoCanInvokeMethod. You must set this function according to your requirements.
        psOutArgs.SetProperty("Invoked", true);
        psOutArgs.SetProperty("RetVal", true);
        $.setReturnValue({ err: false, retVal: psOutArgs });
      }
//You must use the following code for all overridden DoCanInvokeMethod functions:
      else {
        psOutArgs.SetProperty("Invoked", false);
        psOutArgs.SetProperty("RetVal", false);
        $.setReturnValue({ err: false, retVal: psOutArgs });
      }
      return(psOutArgs);
    };
    return pharmacallsvc;
  } ());
}
