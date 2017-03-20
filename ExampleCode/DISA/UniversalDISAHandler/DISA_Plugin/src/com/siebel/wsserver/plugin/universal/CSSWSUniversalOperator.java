package com.siebel.wsserver.plugin.universal;

import com.siebel.wsserver.operator.CSSWSSingletonOperator;
import com.google.gson.JsonObject;

public class CSSWSUniversalOperator extends CSSWSSingletonOperator {
    public static final String COMMAND = "Command";

    /*
     * Return the type of this plugin operator.
     */
    @Override
    public String getType() {
        return "plugin_universal";
    }

    /*
     * Return the version of this plugin operator.
     */
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
    protected void processMessage(JsonObject msg) {
        if (msg.has(COMMAND)) {
            if (msg.get(COMMAND).getAsString().equals("Ready")) {
                JsonObject echo = new JsonObject();
                echo.addProperty("Echo", "Hello, handler!");
                sendMessage(echo);
            }
        }
    }
}