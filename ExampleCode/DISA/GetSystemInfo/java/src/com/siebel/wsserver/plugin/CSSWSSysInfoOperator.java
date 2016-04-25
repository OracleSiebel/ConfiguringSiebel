package com.siebel.wsserver.plugin;

import com.siebel.wsserver.operator.CSSWSSingletonOperator;
import com.google.gson.JsonObject;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * The plugin class depends on disa-api.jar and gson.jar in the lib folder
 */
public class CSSWSSysInfoOperator extends CSSWSSingletonOperator {

    // Logger.getLogger("disa.server") returns DISA system log instance
    // Logs by logger will go into DISA log file.
    //java.util.logging.Logger logger = java.util.logging.Logger.getLogger("disa.server");
    //logger.info("Log debug information.");

    public static final String COMMAND = "Command";

    /*
     * Return the type of this plugin operator.
     */
    @Override
    public String getType() {
        return "plugin_sysinfo";
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
            if (msg.get(COMMAND).getAsString().equals("GetSysInfo")) {
                getSystemInfo();
            }
        }
    }

    /*
     * Gather the required information and send them back to Open UI.
     */
    private void getSystemInfo() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            String hostAddr = host.getHostAddress();
            String hostName = host.getCanonicalHostName();
            JsonObject hostInfo = new JsonObject();
            hostInfo.addProperty("HostAddress", hostAddr);
            hostInfo.addProperty("HostName", hostName);
            hostInfo.addProperty("Processor", Runtime.getRuntime().availableProcessors());
            hostInfo.addProperty("UserHome", System.getProperty("user.home"));
            sendMessage(hostInfo);
        } catch (UnknownHostException ex) {
            JsonObject error = new JsonObject();
            error.addProperty("Error", "UnknownHostException");
            sendMessage(error);
        }
    }
}
