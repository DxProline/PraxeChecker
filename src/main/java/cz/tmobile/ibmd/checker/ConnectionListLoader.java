package cz.tmobile.ibmd.checker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionListLoader {
    // Funkce která načítá soubor pomocí BufferedReadru
    public ConnectionList load(String filename) throws IOException {
        BufferedReader reader;
        ConnectionList connectionList;
        // Vytvořen objekt BufferedReader
        reader = new BufferedReader(new FileReader(filename));
        connectionList = new ConnectionList();
        String line = reader.readLine();
        //Null = dokud nedosáhne konec souboru
        while (line != null) {
            connectionList.getConnections().add(new Connection(line," ", " ",0));
            // přečte další řádek
            line = reader.readLine();
        }
        return connectionList;
    }
}
