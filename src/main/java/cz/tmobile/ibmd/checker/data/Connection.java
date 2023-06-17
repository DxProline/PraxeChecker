package cz.tmobile.ibmd.checker.data;

public class Connection {
 private String serverName;
 private String sourceIpAddress;
 private String sourceLocation;
 private String destinationServerIpAddress;
 private String destinationLocation;
 private String portType;
 private String port;

 public Connection(String serverName, String sourceIpAddress, String sourceLocation, String destinationServerIpAddress, String destinationLocation, String portType, String port) {
  this.serverName = serverName;
  this.sourceIpAddress = sourceIpAddress;
  this.sourceLocation = sourceLocation;
  this.destinationServerIpAddress = destinationServerIpAddress;
  this.destinationLocation = destinationLocation;
  this.portType = portType;
  this.port = port;
 }

 @Override
 public String toString() {
  return
          serverName + ';' +
          sourceIpAddress + ';' +
          sourceLocation + ';' +
          destinationServerIpAddress + ';' +
          destinationLocation + ';' +
          portType + ';' +
          port;
 }

 public String getServerName() {
  return serverName;
 }

 public void setServerName(String serverName) {
  this.serverName = serverName;
 }

 public String getSourceIpAddress() {
  return sourceIpAddress;
 }

 public void setSourceIpAddress(String sourceIpAddress) {
  this.sourceIpAddress = sourceIpAddress;
 }

 public String getSourceLocation() {
  return sourceLocation;
 }

 public void setSourceLocation(String sourceLocation) {
  this.sourceLocation = sourceLocation;
 }

 public String getDestinationServerIpAddress() {
  return destinationServerIpAddress;
 }

 public void setDestinationServerIpAddress(String destinationServerIpAddress) {
  this.destinationServerIpAddress = destinationServerIpAddress;
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

 public String getPort() {
  return port;
 }

 public void setPort(String port) {
  this.port = port;
 }
}