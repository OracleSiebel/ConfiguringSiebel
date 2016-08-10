if (typeof(SiebelAppFacade.SiebelBugsnagNotifier) === "undefined") {
    SiebelJS.Namespace("SiebelAppFacade.SiebelBugsnagNotifier");
 
    define("siebel/custom/bugsnagNotifier", ["http://d2wy8f7a9ursnm.cloudfront.net/bugsnag-2.min.js"],
        function () {
            try {
                // The apiKey ties your notifier to your Bugsnag project
                Bugsnag.apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                 
                // berforeNotify allows to add contextual information 
                Bugsnag.beforeNotify = function(payload, metaData) { 
                    var viewName, viewTitle, appletRawRecordSet;
                     
                    try { viewName = SiebelApp.S_App.GetActiveView().GetName() } catch (e) { viewName = "N/A";} 
                    try { viewTitle = SiebelApp.S_App.GetActiveView().GetName() } catch (e) { viewTitle = "N/A";} 
                    try { appletRawRecordSet = SiebelApp.S_App.GetActiveView().GetActiveApplet().GetRawRecordSet() } catch (e) { appletRawRecordSet = "N/A";} 
                 
                    metaData.ViewName = {"View Name": viewName,
                                         "View Title": viewTitle};
                                          
                    metaData.ViewData = {"View Data": appletRawRecordSet};
                     
                    metaData.User =     {"Login Id": SiebelApp.S_App.GetProfileAttr("Me.Id"),
                                         "Login Name": SiebelApp.S_App.GetProfileAttr("Me.Login Name"),
                                         "User Responsibilities": SiebelApp.S_App.GetProfileAttr("Me.User Responsibilities")};
                }
            }
            catch (e)
            {
                SiebelJS.Log(e.toString());
            }

            setTimeout(function(){what.this();},0);

        }
    )
};