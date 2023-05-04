package cz.tmobile.ibmd.checker;

public class Connection {
 private String severIPAddress;
 private String serverName;
 private String serverLocation;
 private Integer serverPort;

 public Connection(String severIPAddress, String serverName, String serverLocation, Integer serverPort) {
  this.severIPAddress = severIPAddress;
  this.serverName = serverName;
  this.serverLocation = serverLocation;
  this.serverPort = serverPort;
 }

 public String getSeverIPAddress() {
  return severIPAddress;
 }

 public void setSeverIPAddress(String severIPAddress) {
  this.severIPAddress = severIPAddress;
 }

 public String getServerName() {
  return serverName;
 }

 public void setServerName(String serverName) {
  this.serverName = serverName;
 }

 public String getServerLocation() {
  return serverLocation;
 }

 public void setServerLocation(String serverLocation) {
  this.serverLocation = serverLocation;
 }

 public Integer getServerPort() {
  return serverPort;
 }

 public void setServerPort(Integer serverPort) {
  this.serverPort = serverPort;
 }
}
