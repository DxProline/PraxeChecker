package cz.ibmd.checker;

import cz.ibmd.checker.data.Connection;
import cz.ibmd.checker.data.ConnectionList;
import cz.ibmd.checker.data.ProcessList;
import cz.ibmd.checker.data.Result;
import cz.ibmd.checker.loaders.ConnectionListLoader;
import cz.ibmd.checker.loaders.ProcessListLoader;
import cz.ibmd.checker.processing.Checker;

import java.io.IOException;

/*
Spouštění programu v Příkazovém řádku
 */
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