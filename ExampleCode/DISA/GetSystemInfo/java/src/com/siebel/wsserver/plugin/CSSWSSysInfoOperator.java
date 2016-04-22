package com.siebel.wsserver.plugin;

import com.siebel.wsserver.operator.CSSWSSingletonOperator;
import com.google.gson.JsonObject;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CSSWSSysInfoOperator extends CSSWSSingletonOperator {

    public static final String MESSAGE = "Message";
    public static final String COMMAND = "Command";

    @Override
    public String getType() {
        return "plugin_sysinfo";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    protected void processMessage(JsonObject msg) {
        if (msg.has(COMMAND)) {
            if (msg.get(COMMAND).getAsString().equals("GetSysInfo")) {
                getSystemInfo();
            }
        }
    }

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
