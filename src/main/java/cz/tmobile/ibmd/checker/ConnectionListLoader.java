package cz.tmobile.ibmd.checker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionListLoader {
    // Funkce která čte soubor pomocí BufferedReadru
    public ConnectionList load(String filename) throws IOException {
        BufferedReader reader;
        // Vytvořen objekt BufferedReader
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            // přečte další řádek
            line = reader.readLine();
        }
        return null;
    }
}
