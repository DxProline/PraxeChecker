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

        //Zachytí výsledek checkeru
        Result result = checker.check(processList, connectionList);
        for (Connection connection : result.getMissingServers()){
            System.out.println("Missing;" + connection);
        }
        for (Connection connection : result.getRemovedServers()){
            System.out.println("Removed;" + connection);
        }
    }
}