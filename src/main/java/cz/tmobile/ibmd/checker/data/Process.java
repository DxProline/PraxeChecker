package cz.tmobile.ibmd.checker.data;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private String processName;
    private String host;
    private String port;

    public Process(String processName, String host, String port) {
        this.processName = processName;
        this.host = host;
        this.port = port;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
