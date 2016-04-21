**![](/confluence/plugins/servlet/confluence/placeholder/macro?definition=e3RvY30&locale=en_GB&version=2)**

# **Chapter 1\. Overview**

Desktop Integration Siebel Agent (DISA), as a framework, can be extended and integrated with customized functionalities. With the new extensibility support, users can now create their own custom plug-ins and integrate them with DISA. This will help users fulfill their specific requirements and benefit from DISA's ability as a bridge between Siebel Open UI application running in browser and local files, resources, programs, etc., increasing their productivity and work efficiency.

This document describes how to develop custom plug-ins based on DISA APIs and integrate them with DISA framework.

As in Fig 1.1, to develop based on DISA framework, for each component:

*   At Siebel OpenUI side, create a WSHandler for the component
    *   Implement <span style="color: rgb(255,0,0);">**callbacks**</span> if necessary
    *   Implement <span style="color: rgb(255,153,0);">**component specific logic**</span>
*   At DISA side, create a component Operator based on Operator base class
    *   Implement <span style="color: rgb(255,0,0);">**interfaces**</span>
    *   Implement <span style="color: rgb(255,153,0);">**component specific logic**</span>
*   At both sides, call **<span style="color: rgb(153,204,255);">interfaces of framework</span>** within component logic to send messages between both sides

![](/confluence/download/attachments/193499699/worddav5b33c82e7e0b1dbea920ec54c591323b.png?version=1&modificationDate=1460430243000&api=v2 "SEBLCC  - Common Components > DISA Development Guide > worddav5b33c82e7e0b1dbea920ec54c591323b.png")

**Fig 1.1 Overview**

# **Chapter 2\. Interfaces**

**WSHandler**

<span style="line-height: 20.0px;">The following WSHandler and related codes could be added anywhere needed, however, generally we recommend to add it to the pmodel of corresponding component.</span>

1.  <span style="line-height: 1.42857;">Define the **Component Type** and **Component Version** constants of WSHandler.</span>
    *   Specify a unique string with prefix "plugin_" as **Component Type**, to identify the WSHandler in Siebel OpenUI and its corresponding Operator in DISA plug-in.  
        The string at both sides should be exact the same so that they can communicate with each other.  
        This string will be used as the argument when calling SiebelApp.WebSocketManager.CreateWSHandler to create the customized WSHandler.
    *   <span style="line-height: 1.42857;">Specify the</span> **Component Version** <span style="line-height: 1.42857;">to support component version check between WSHandler and Operator.</span>  
        Constant key: WS_<span style="color: rgb(0,112,192);">_COMPTYPE_</span>_VERSION. Replace <span style="color: rgb(0,112,192);">_COMPTYPE_</span> in middle with Component Type string defined above.  
        Constant value: in MAJOR.MINOR.PATCH format. Modify the version number according to rules defined in <span style="line-height: 1.42857;">[Appendix A. Version Check (Backward Compatibility)](https://confluence.oraclecorp.com/confluence/display/SEBLCC/DISA+Development+Guide#DISADevelopmentGuide-AppendixA.VersionCheck(BackwardCompatibility))</span>.  
        In order to pass the version check, the component version of WSHanlder at Siebel OpenUI should have the same or lower MAJOR version than the Operator version at DISA.

        <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=js|linenumbers=true|theme=Eclipse|title=Example - Constants Definitions" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9anN8dGhlbWU9RWNsaXBzZXx0aXRsZT1FeGFtcGxlIC0gQ29uc3RhbnRzIERlZmluaXRpb25zfGxpbmVudW1iZXJzPXRydWV9&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

        <tbody>

        <tr>

        <td class="wysiwyg-macro-body">

        <pre>// The two specific constants should be defined in common SiebelApp.Constants in the following way
        // So that the Component Type and Version could be picked up by DISA Framework
        var consts = SiebelJS.Dependency("SiebelApp.Constants");
        consts.set("WS_COMPONENT_TYPE_PLUGIN_SAMPLE","plugin_sample");
        consts.set("WS_PLUGIN_SAMPLE_VERSION", "1.0.0");</pre>

        </td>

        </tr>

        </tbody>

        </table>

2.  Define the **WSHandler** (and its getter if necessary)
    *   Call **SiebelApp.WebSocketManager.CreateWSHandler** with Component Type defined in Step 1 as the argument.
    *   Set callbacks if necessary, including OnMessage, OnFail, and OnClose.  
        **OnMessage(msg, fileName)**: called when getting message from Operator at DISA  
        **OnFail()**: called when failed to send message to DISA  
        **OnClose()**: called when connection to DISA is lost

        <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=js|linenumbers=true|theme=Eclipse|title=Example - WSHandler Definition" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9anN8dGhlbWU9RWNsaXBzZXx0aXRsZT1FeGFtcGxlIC0gV1NIYW5kbGVyIERlZmluaXRpb258bGluZW51bWJlcnM9dHJ1ZX0&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

        <tbody>

        <tr>

        <td class="wysiwyg-macro-body">

        <pre>var sampleHandler = null;

        function getSampleHandler() {
            if (sampleHandler === null) {
                sampleHandler = SiebelApp.WebSocketManager.CreateWSHandler(consts.get("WS_COMPONENT_TYPE_PLUGIN_SAMPLE"));

                sampleHandler.OnClose = onWSClose;
                sampleHandler.OnFail = onWSSendFail;
                sampleHandler.OnMessage = onWSMessage;
            }

            return sampleHandler;
        }

        function onWSMessage(msg, fileName) {
            if (msg instanceof Blob) {
                // Message is binary data
                // fileName is the ID of the binary data
                handleFile(msg, fileName);
            } else {
                // Message is in JSON format
                // fileName is undefined
                handleMsg(msg);
            }
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
        }</pre>

        </td>

        </tr>

        </tbody>

        </table>

3.  Implement **component logic** functions if necessary

    <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=js|linenumbers=true|theme=Eclipse|title=Example - Component Logic Functions" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9anN8dGhlbWU9RWNsaXBzZXx0aXRsZT1FeGFtcGxlIC0gQ29tcG9uZW50IExvZ2ljIEZ1bmN0aW9uc3xsaW5lbnVtYmVycz10cnVlfQ&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

    <tbody>

    <tr>

    <td class="wysiwyg-macro-body">

    <pre>// Called by onWSMessage event handler
    function handleMsg(msg) {
        console.log("JSON message received: " + JSON.stringify(msg));
    }

    // Called by onWSMessage event handler
    function handleFile(msg, fileName) {
        console.log("File " + fileName + " received from DISA.");
    }

    // Called by onWSClose or onWSSendFail event handler
    function handleException(err) {
        // Adds other error handling logic
        console.log(err);
    }</pre>

    </td>

    </tr>

    </tbody>

    </table>

4.  Call **SendMessage(msg, fileName)** method of WSHandler to send message to the corresponding operator at DISA

    <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=js|linenumbers=true|theme=Eclipse|title=Example - SendMessage of WSHandler" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9anN8dGhlbWU9RWNsaXBzZXx0aXRsZT1FeGFtcGxlIC0gU2VuZE1lc3NhZ2Ugb2YgV1NIYW5kbGVyfGxpbmVudW1iZXJzPXRydWV9&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

    <tbody>

    <tr>

    <td class="wysiwyg-macro-body">

    <pre>var handler = getSampleHandler();

    // This JSON message is what corresponding operator at DISA side should expect
    var msgJSON = {
        firstName: "First",
        lastName: "Last"
    };
    handler.SendMessage(msgJSON);

    // Another format supported is binary data (Blob is recommended, ArrayBuffer is also supported)
    // With binary data as the first arg, the second arg is required
    var fileContent = new Blob([JSON.stringify(msgJSON, null, 2)], {type : 'application/json'});
    var fileName = "test.json";
    handler.SendMessage(fileContent, fileName);</pre>

    </td>

    </tr>

    </tbody>

    </table>

5.  Call **Unregister()** method of WSHanlder to release the handler itself once no longer used, and it'll also send command to DISA to unregister the corresponding operator.  
    For CSSWSSingletonOperator type, the operator would not actually be released though. For CSSWSOperator type, it would.

    <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=js|linenumbers=true|theme=Eclipse|title=Example - Unregister of WSHandler" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9anN8dGhlbWU9RWNsaXBzZXx0aXRsZT1FeGFtcGxlIC0gVW5yZWdpc3RlciBvZiBXU0hhbmRsZXJ8bGluZW51bWJlcnM9dHJ1ZX0&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

    <tbody>

    <tr>

    <td class="wysiwyg-macro-body">

    <pre>/*
     * One common called timing would be in the applet pmodel EndLife, for example:
     * EmailPModel.prototype.EndLife = function () {
     *     unregisterSampleHandler.call(this);
     * };
     */
    function unregisterSampleHandler() {
        if (sampleHandler) {
            sampleHandler.Unregister();
            sampleHandler = null;
        }
    }</pre>

    </td>

    </tr>

    </tbody>

    </table>

**Custom Operator** **Plugin**

1.  Create a plugin for component inherit from Operator base class
    *   <span style="line-height: 1.42857;">Create</span> **a new Java Package** <span style="line-height: 1.42857;">for the component operator class and other related files if any.</span>
    *   Create **a new component Operator class**.
        *   Inherit from CSSWSSingletonOperator if the operator is designed for sequential tasks, and does not have request for parallel execution. The request for the same operator type from different components will be place in one queue and processed in one single thread.
        *   Inherit from CSSWSOperator if the operator is required to handling tasks in parallel, or tasks are expected to execute for a long time. Each component will be assigned to a new operator instance, and with separate thread for each operator, tasks can be processed paralleled.
    *   Implement **getType**, return component type string. This string should be in accordance with the CompType specified when calling CreateWSHandler to create the corresponding WSHandler at Siebel OpenUI side. A custom plugin type must start with string "plugin_" to indicate it is a plugin operator.
    *   Implement **getVersion**, return component version string, to support comp version check between WSHandler and Operator. The version should be in MAJOR.MINOR.PATCH format. Modify the version number according to rules defined in [Appendix A. Version Check (Backward Compatibility)](https://confluence.oraclecorp.com/confluence/display/SEBLCC/DISA+Development+Guide#DISADevelopmentGuide-AppendixA.VersionCheck(BackwardCompatibility)).
    *   Implement **processMessage**, add the task process logic here, if the operator message queue have new message added, this method will be called with the JSON format message as the parameter.
    *   Implement **component logic** needed as "private" methods.
    *   Call **sendMessage** to send JsonObject type message from DISA to Siebel OpenUI.
    *   Call **sendFile** to send file from DISA to Siebel OpenUI, with fileName (full name including path) as parameter.

        <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=java|linenumbers=true|theme=Eclipse|title=Example - Plug-in Operator" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9amF2YXx0aGVtZT1FY2xpcHNlfHRpdGxlPUV4YW1wbGUgLSBQbHVnLWluIE9wZXJhdG9yfGxpbmVudW1iZXJzPXRydWV9&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

        <tbody>

        <tr>

        <td class="wysiwyg-macro-body">

        <pre>package com.domain.disa.plugin;

        /*
         * The plugin class depends on disa-api.jar and gson.jar in the lib folder 
         */
        public class SampleOperator extends com.siebel.wsserver.operator.CSSWSSingletonOperator {
            // Logger.getLogger("disa.server") returns DISA system log instance
            // Logs by logger will go into DISA log file.
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger("disa.server");

            public static final String FILE_NAME = "FileName";

            @Override
            public String getType() {
                return "plugin_sample";
            }

            @Override
            public String getVersion() {
                return "1.0.0";
            }

            /*
             * The main logic to process the message DISA gets from Siebel OpenUI
             * Any message DISA gets for this component type will be put in a 
             * queue, and this method will process messages in the queue.
             *
             * @param msg the current message in message queue
             */
            @Override
            protected void processMessage(com.google.gson.JsonObject msg) {
                // Sample code calling sendFile and sendMessage
                if (msg.has(FILE_NAME)) {
                    // if the current message is a file, the message should
                    // include a key named "FileName" and the value is the local
                    // path to this file.
                    String fileName = msg.get(FILE_NAME).getAsString();
                    // Call sendFile(fileName) to send file to Siebel OpenUI
                    sendFile(fileName);
                } else {
                    // The basic text message format is JSON
                    com.google.gson.JsonObject responseMsg = new com.google.gson.JsonObject();
                    responseMsg.addProperty("Echo", msg.toString());
                    // Call sendMessage(msg) to send JSON message to Siebel OpenUI
                    sendMessage(responseMsg);
                    logger.info("Echo Message back: " + responseMsg.toString());
                }
            }
        }</pre>

        </td>

        </tr>

        </tbody>

        </table>

2.  Set META-INF for the newly created plugin
    *   Create a folder named "META-INF" in the plugin source folder (the root folder of package folders), if it does not exist.
    *   Create a folder named "services" inside the "META-INF" folder.
    *   Create a new text file named exactly "com.siebel.wsserver.operator.CSSWSOperator", without file extension.
    *   Add new class names including package name to the file, each class name takes one line.

        <table class="wysiwyg-macro" data-macro-name="code" data-macro-parameters="language=java|linenumbers=true|theme=Eclipse|title=Example - META-INF for Plug-in" style="background-image: url(/confluence/plugins/servlet/confluence/placeholder/macro-heading?definition=e2NvZGU6bGFuZ3VhZ2U9amF2YXx0aGVtZT1FY2xpcHNlfHRpdGxlPUV4YW1wbGUgLSBNRVRBLUlORiBmb3IgUGx1Zy1pbnxsaW5lbnVtYmVycz10cnVlfQ&amp;locale=en_GB&amp;version=2); background-repeat: no-repeat;" data-macro-body-type="PLAIN_TEXT">

        <tbody>

        <tr>

        <td class="wysiwyg-macro-body">

        <pre>com.domain.disa.plugin.SampleOperator</pre>

        </td>

        </tr>

        </tbody>

        </table>

3.  Compile the class and pack the META-INF folder in a jar file.

# **Chapter 3\. Deployment**

At DISA side:

1.  Put plugin jars in _<DISA_HOME>\DesktopIntSiebelAgent\plugins_ folder.
2.  Restart DISA to reload all the plug-ins.

At Siebel OpenUI side:

1.  Deploy the js file including new WSHanlder definition in Siebel OpenUI application.

# **Appendix A. Version Check (Backward Compatibility)**

DISA is supposed to be backward compatible with multiple versions of Siebel OpenUI application. From customization perspective, Component Version needs to be checked to support backward compatibility.

*   <span style="line-height: 1.42857;">Component Version should be defined in MAJOR.MINOR.PATCH format.</span>  
    Modify the version according to following rules, increment the:
    *   MAJOR version when making incompatible API changes
    *   MINOR version when adding functionality in a backwards-compatible manner
    *   PATCH version when making backwards-compatible bug fixes
*   <span style="line-height: 1.42857;">Component Version is defined at both sides:</span> **Component at Siebel OpenUI** <span style="line-height: 1.42857;">and</span> **Component Operator at DISA**<span style="line-height: 1.42857;">.</span>
*   Component Version check happens on the first request of current component. It will report error (asking for upgrade) if the MAJOR version of **Operator** is lower.