package cz.tmobile.ibmd.checker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ConnectionListLoader connectionListLoader = new ConnectionListLoader();
        ProcessListLoader processListLoader = new ProcessListLoader();
        Checker checker = new Checker();
        //Načte na soubor Connection List který dostane na vstup.
        ConnectionList connectionList = connectionListLoader.load(args[0]);
        ProcessList processList = processListLoader.load(args[1]);
        // for (Connection connection : connectionList.getConnections()) {
        //     System.out.println(connection.getServerName());
        //     System.out.println(connection.getDestinationLocation());
        //     System.out.println(connection.getDestinationServer());
        // }
        // for (Process process : processList.getProcesses()) {
        //     System.out.println(process.getProcessName());
        //     System.out.println(process.getHost());
        // }

        //Zachytí výsledek checkeru
        Result result = checker.check(processList, connectionList);
        System.out.println("Missing");
        for (Connection connection : result.getMissingServers()){
            System.out.println(connection);
        }
        System.out.println("Removed");
        for (Connection connection : result.getRemovedServers()){
            System.out.println(connection);
        }
    }
}