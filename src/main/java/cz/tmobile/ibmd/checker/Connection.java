package cz.tmobile.ibmd.checker;

public class Connection {
 private String serverName;
 private String sourceServer;
 private String sourceLocation;
 private String destinationServer;
 private String destinationLocation;
 private String portType;
 private Integer port;

 public Connection(String serverName, String sourceServer, String sourceLocation, String destinationServer, String destinationLocation, String portType, Integer port) {
  this.serverName = serverName;
  this.sourceServer = sourceServer;
  this.sourceLocation = sourceLocation;
  this.destinationServer = destinationServer;
  this.destinationLocation = destinationLocation;
  this.portType = portType;
  this.port = port;
 }

 @Override
 public String toString() {
  return "Connection{" +
          "serverName='" + serverName + '\'' +
          ", sourceServer='" + sourceServer + '\'' +
          ", sourceLocation='" + sourceLocation + '\'' +
          ", destinationServer='" + destinationServer + '\'' +
          ", destinationLocation='" + destinationLocation + '\'' +
          ", portType='" + portType + '\'' +
          ", port=" + port +
          '}';
 }

 public String getServerName() {
  return serverName;
 }

 public void setServerName(String serverName) {
  this.serverName = serverName;
 }

 public String getSourceServer() {
  return sourceServer;
 }

 public void setSourceServer(String sourceServer) {
  this.sourceServer = sourceServer;
 }

 public String getSourceLocation() {
  return sourceLocation;
 }

 public void setSourceLocation(String sourceLocation) {
  this.sourceLocation = sourceLocation;
 }

 public String getDestinationServer() {
  return destinationServer;
 }

 public void setDestinationServer(String destinationServer) {
  this.destinationServer = destinationServer;
 }

 public String getDestinationLocation() {
  return destinationLocation;
 }

 public void setDestinationLocation(String destinationLocation) {
  this.destinationLocation = destinationLocation;
 }

 public String getPortType() {
  return portType;
 }

 public void setPortType(String portType) {
  this.portType = portType;
 }

 public Integer getPort() {
  return port;
 }

 public void setPort(Integer port) {
  this.port = port;
 }
}