if (typeof SiebelApp.UniversalDISAHandler === "undefined") {
    SiebelJS.Namespace("SiebelApp.UniversalDISAHandler");

    define("siebel/custom/UniversalDISAHandler", [], function () {
        SiebelApp.UniversalDISAHandler = (function () {
            var DISA_UNIVERSALHANDLER = "plugin_universal";
            var consts = SiebelJS.Dependency("SiebelApp.Constants");
            consts.set("WS_" + DISA_UNIVERSALHANDLER.toUpperCase() + "_VERSION", "1.0.0");

            function UniversalDISAHandler() {
                var handler = SiebelApp.WebSocketManager.CreateWSHandler(DISA_UNIVERSALHANDLER);

                // Communications with DISA are asynchronous.
                // We define handler functions here to deal with responses from DISA,
                // such as a message or communication failure conditions.
                handler.OnClose = onWSClose.bind(this);
                handler.OnFail = onWSSendFail.bind(this);
                handler.OnMessage = onWSMessage.bind(this);

                // Tell DISA plugin that OpenUI handler is ready
                // "Command" and "Ready" are custom message protocol.
                // The shape and content of the message is entirely up to the developer.
                SiebelApp.EventManager.addListner("AppInit", function () {
                    var msgJSON = {};
                    msgJSON["Command"] = "Ready";
                    handler.SendMessage(msgJSON);
                }, this);

                    return handler;
                }

            // This indicates Siebel OpenUI with DISA connection was lost
            // Maybe because Siebel OpenUI never establishes connection with DISA due to various reasons
            // Maybe because DISA exited (by user) or crashed
            // Reset state or other variables if necessary
            function onWSClose() {
                handleException("Connection to DISA was lost");
            }

            // Normally this indicates something wrong with communication attempt to operator at DISA
            // Maybe because Siebel OpenUI never establishes connection with DISA due to various reasons
            // Maybe because the version number at two sides are not matched, operator version should be equal or newer
            // Reset state or other variables if necessary
            function onWSSendFail() {
                handleException("Failed to send message to DISA");
            }

            function onWSMessage(msg, fileName) {
                handleMsg.call(this, msg);
            }

            // Called by onWSMessage event handler
            function handleMsg(msg) {
                SiebelJS.Log("UniversalDISAHandler: JSON message received: " + JSON.stringify(msg));
                // HERE add your own business logic when receiving messages from DISA, like phone number
            }

            // Called by onWSClose or onWSSendFail event handler
            function handleException(err) {
                // Adds other error handling logic
                SiebelJS.Log(err);
            }

            return new UniversalDISAHandler();
        })();
    });
}
