if (typeof(SiebelAppFacade.DISAIntegrationPM) === "undefined") {

	SiebelJS.Namespace("SiebelAppFacade.DISAIntegrationPM");
	define("siebel/custom/DISAIntegrationPM", ["siebel/pmodel"],
		function () {
		SiebelAppFacade.DISAIntegrationPM = (function () {

			var consts = SiebelJS.Dependency("SiebelApp.Constants");
			var DISA_PLUGIN = "plugin_sysinfo";
			consts.set("WS_"+DISA_PLUGIN.toUpperCase()+"_VERSION", "1.0.0");
			
			var DISAHandler = null;
			var attachedHandlers = 0;

			function DISAIntegrationPM(pm) {
				SiebelAppFacade.DISAIntegrationPM.superclass.constructor.apply(this, arguments);
			}

			SiebelJS.Extend(DISAIntegrationPM, SiebelAppFacade.PresentationModel);

			DISAIntegrationPM.prototype.Setup = function (propSet) {
				SiebelAppFacade.DISAIntegrationPM.superclass.Setup.apply(this, arguments);

				// Register a method to interface with DISA. This will be called from the PR
				this.AddMethod("callDISAPlugin", callDISAPlugin, {sequence:false, scope:this});
				// Register a method which will carry the DISA plugin response back to the PR
				this.AddMethod("DISAResponse", function() {}, {});
				// Increase the number of attached handlers
				attachedHandlers++;
			}

			DISAIntegrationPM.prototype.EndLife = function () {
				// Called when the PM is no longer needed
				SiebelAppFacade.DISAIntegrationPM.superclass.EndLife.apply(this, arguments);
				// Reduce attachHandlers and, if necessary, destroy the WS Handler
				if (--attachedHandlers == 0) {
					unregisterDISAHandler.call();
				}
			}
			
			function callDISAPlugin() {
				var handler = getDISAHandler.call(this);
				
				// here we create an object containing data which the Java application will read.
				// Neither "Command", nor "GetSysInfo" are DISA specific.
				// The shape and content of the message is entirely up to the developer.
				var msgJSON = {};
				msgJSON["Command"] = "GetSysInfo";
				handler.SendMessage(msgJSON);
				// the message is sent asychronously, so once the command is sent, nothing further happens
				// within the PR/PM until a message is received.
			}
			
			function getDISAHandler() {
				if (DISAHandler === null) {
					DISAHandler = SiebelApp.WebSocketManager.CreateWSHandler(DISA_PLUGIN);

					// communications with DISA are asynchronous. We define handler functions here to deal with
					// possible responses from DISA, such as a message or communication failure conditions.
					DISAHandler.OnClose = onWSClose.bind(this);
					DISAHandler.OnFail = onWSSendFail.bind(this);
					DISAHandler.OnMessage = onWSMessage.bind(this);
				}

				return DISAHandler;
			}

			function onWSMessage(msg, fileName) {
				// this is the result of callDISAPlugin if all goes well
				handleMsg.call(this,msg);
			}

			// Normally this indicates something wrong with communication attempt to operator at DISA
			// Maybe because Siebel OpenUI never establishes connection with DISA due to various reasons
			// Maybe because the version number at two sides are not matched, operator version should be equal or newer
			// Reset state or other variables if necessary
			function onWSSendFail() {
				handleException("Failed to send message to DISA");
			}

			// This indicates Siebel OpenUI with DISA connection was lost
			// Maybe because Siebel OpenUI never establishes connection with DISA due to various reasons
			// Maybe because DISA exited (by user) or crashed
			// Reset state or other variables if necessary
			function onWSClose() {
				handleException("Connection to DISA was lost");
			}

			// Called by onWSMessage event handler
			function handleMsg(msg) {
				// now we received the response from the DISA plugin, the pass it to the waiting method code
				// in the PR
				this.ExecuteMethod("DISAResponse",msg);
				// and log the detail for good measure
				SiebelJS.Log(this.Get("GetName")+": JSON message received: " + JSON.stringify(msg));
			}

			// Called by onWSClose or onWSSendFail event handler
			function handleException(err) {
				// Adds other error handling logic
				SiebelJS.Log(err);
			}

			function unregisterDISAHandler() {
				if (DISAHandler) {
					DISAHandler.Unregister();
					DISAHandler = null;
				}
			}

			return DISAIntegrationPM;
		}());
		return "SiebelAppFacade.DISAIntegrationPM";
	})
}
