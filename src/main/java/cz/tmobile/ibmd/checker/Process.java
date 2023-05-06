package cz.tmobile.ibmd.checker;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private String processName;
    private String host;
    private Integer port;

    public Process(String processName, String host, Integer port) {
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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
