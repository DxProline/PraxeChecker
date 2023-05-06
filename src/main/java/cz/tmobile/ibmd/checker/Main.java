package cz.tmobile.ibmd.checker;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConnectionListLoader connectionListLoader = new ConnectionListLoader();
        //Načte na soubor Connection List který dostane na vstup.
        ConnectionList connectionList = connectionListLoader.load("C:\\Users\\jenda\\OneDrive\\Dokumenty\\ConnectionList.txt");
        for (Connection connection : connectionList.getConnections()) {
            System.out.println(connection.getServerName());
            System.out.println(connection.getDestinationLocation());
        }
    }
}