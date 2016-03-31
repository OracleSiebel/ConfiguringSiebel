if (typeof (SiebelApp.FileLoader) == "undefined") {
    Namespace('SiebelApp.FileLoader');
    SiebelApp.FileLoader = (function(){
     // dependencies
        var utils = SiebelApp.Utils;
        // JQM 
     //   var isMobile = navigator.userAgent.match(/(iPad)|(iPhone)|(iPod)|(android)|(webOS)/i);        
        var loader = new FileLoader;
        var m_mapFiles = {};
        var m_callbackObject;
        var m_callback;
        // public API
        function FileLoader() {
            var instance;
            // rewrite the constructor
            FileLoader = function FileLoader() {
                return instance;
            };
            // carry over the prototype properties
            FileLoader.prototype = this;
            //instance specification
            instance = new FileLoader();
            // reset the constructor pointer
            instance.constructor = FileLoader;
            return instance;
        }
        function OnLoad(data, status){
            if(status=="success"){
                var arrKeys = data.split("[");
                for(var keyIndex=0;keyIndex<arrKeys.length;keyIndex++){
                    var strKey = arrKeys[keyIndex];
                    if(utils.IsEmpty(arrKeys[keyIndex])){
                        continue;
                    }
                    var arrSubKey = strKey.split("]");
                    var arrFiles = arrSubKey[1].split("\n");
                    m_mapFiles[arrSubKey[0]] = [];
                    for(var fileIndex=0;fileIndex<arrFiles.length;fileIndex++){
                        if(arrFiles[fileIndex].charCodeAt(0)!=13 && !isNaN(arrFiles[fileIndex].charCodeAt(0)))
                            m_mapFiles[arrSubKey[0]].push("order!"+arrFiles[fileIndex]);
                    }
                }
                if (!(SiebelApp.S_App.IsMobileApplication() === "true"))
                   SiebelApp.FileLoader.LoadKey([   "CalRenderer", 
                                                    "Controls" , 
                                                    "Configurator",
                                                    "CommToolbar",
                                                ], m_callbackObject, m_callback);
                // JQM
                else
                   SiebelApp.FileLoader.LoadKey([   "top", 
                                                    "Stub", 
                                                    "TreeRenderer", 
                                                    "ListRenderer", 
						    "JQMListRenderer",
                                                    "JQMMapCtrl",
                                                    "CalRenderer", 
                                                    "AutoComplete", 
                                                    "Controls",
                                                    "jqmjsNavBar" 
                                                ],  m_callbackObject, m_callback);
            }
        }
        function ErrorHandler(error){
            try{
                SiebelJS.Log(error);
            }
            catch(error)
            {
                //No-Op
            }
        }
        FileLoader.prototype.LoadKey = function(key, object, callback){
            var baseScriptDir = SiebelApp.S_App.GetPageURL().split("start.swe")[0]+SiebelApp.S_App.GetScriptDir();
            require.config({
                baseUrl: baseScriptDir,
                waitSeconds: 30
              });
            require.onError = ErrorHandler;
            if(key instanceof Array){
                var arrCumKeys = [];
                for(var keyIndex=0;keyIndex<key.length;keyIndex++){
                    arrCumKeys = arrCumKeys.concat(m_mapFiles[key[keyIndex]]);
                }
                require( arrCumKeys,
                        function() {
                            callback.call(object)                            
                        });
            }
            else{
                require( m_mapFiles[key],
                        function(files) {
                            callback.call(object)                            
                        });
            }
        };
        FileLoader.prototype.Load = function(object , callback){
            var baseScriptDir = SiebelApp.S_App.GetPageURL().split("start.swe")[0]+SiebelApp.S_App.GetScriptDir();
//            require( ["siebel/fileloader", baseScriptDir+"sample.txt", "http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAbxzsFE1Tnds0s8NIJqLUMxRxLUBlQ194WdVPHGj2N6p3EiVV5BRI3-2ofSS8vPN7zzz41hH1cMeUEQ"],
//                    function(myModule) {
//                        console.log("Hi");                            
//                    });
            m_callbackObject = object;
            m_callback = callback;
            require.config({
                baseUrl: baseScriptDir,
                waitSeconds: 30
              });
            //Moving $.get call to AjaxRequestMgr
           SiebelApp.AjaxRequestMgr.Get(baseScriptDir+"scriptfiles.txt", OnLoad);
        };
        return loader;
    }());
}
