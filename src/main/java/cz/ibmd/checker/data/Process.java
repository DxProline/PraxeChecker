package cz.ibmd.checker.data;

public class Process {
    private String processName;
    private String destinationServerIpAddress;
    private String port;

    public Process(String processName, String destinationServerIpAddress, String port) {
        this.processName = processName;
        this.destinationServerIpAddress = destinationServerIpAddress;
        this.port = port;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getDestinationServerIpAddress() {
        return destinationServerIpAddress;
    }

    public void setDestinationServerIpAddress(String destinationServerIpAddress) {
        this.destinationServerIpAddress = destinationServerIpAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
