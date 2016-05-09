//Regenerate using:http://duncanford.github.io/prpm-code-generator/?prpm=PM&object=DesktopForm&name=OracleMaps&userprops=Street Address,ZIP Code,City,Country&comments=No&logging=No
if (typeof(SiebelAppFacade.OracleMapsPM) === "undefined") {

 SiebelJS.Namespace("SiebelAppFacade.OracleMapsPM");
 define("siebel/custom/OracleMapsPM", ["siebel/pmodel"],
  function () {
   SiebelAppFacade.OracleMapsPM = (function () {

    var consts = SiebelJS.Dependency("SiebelApp.Constants");
    var utils = SiebelJS.Dependency("SiebelApp.Utils");

    function OracleMapsPM(pm) {
     SiebelAppFacade.OracleMapsPM.superclass.constructor.apply(this, arguments);
    }

    SiebelJS.Extend(OracleMapsPM, SiebelAppFacade.PresentationModel);

    OracleMapsPM.prototype.Init = function () {
     SiebelAppFacade.OracleMapsPM.superclass.Init.apply(this, arguments);
    }

    OracleMapsPM.prototype.Setup = function (propSet) {
     SiebelAppFacade.OracleMapsPM.superclass.Setup.apply(this, arguments);
     processCustomUserProperty.call(this,propSet,"Street Address")
     processCustomUserProperty.call(this,propSet,"ZIP Code")
     processCustomUserProperty.call(this,propSet,"City")
     processCustomUserProperty.call(this,propSet,"Country")
    }

    function processCustomUserProperty(propSet,propertyName) {
     var userProps = propSet.GetChildByType(consts.get("SWE_APPLET_PM_PS"));
      if (userProps) {
      var propVal = userProps.GetProperty(propertyName);
      if (!utils.IsEmpty(propVal)) {
      this.AddProperty(propertyName, propVal);
     }} else {
     }
    }

    return OracleMapsPM;
   }()
  );
  return "SiebelAppFacade.OracleMapsPM";
 })
}
