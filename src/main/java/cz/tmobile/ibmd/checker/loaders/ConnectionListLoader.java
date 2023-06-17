package cz.tmobile.ibmd.checker.loaders;

import cz.tmobile.ibmd.checker.data.Connection;
import cz.tmobile.ibmd.checker.data.ConnectionList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionListLoader {
    //LineCounter určuje číslo řádku který Loader Právě načetl
    //tato hodnota se pak využívá pro hlášení na jakém řádku došlo k chybě
    private int lineCounter;
    // Funkce která načítá soubor pomocí BufferedReadru
    public ConnectionList load(String filename) throws IOException {
        BufferedReader reader;
        ConnectionList connectionList;
        // Vytvořen objekt BufferedReader
        reader = new BufferedReader(new FileReader(filename));

        connectionList = new ConnectionList();
        String line = reader.readLine();
        lineCounter++;

        //Null = dokud nedosáhne konec souboru
        while (line != null) {

            //Rozdělí řádek načtený ze souboru na sloupce oddělené středníkem
            String[] output = line.split(";");
            connectionList.getConnections().add(new Connection(output[0], output[1], output[2],output[3], output[4], output[5], output[6]));
            // přečte další řádek
            line = reader.readLine();
            lineCounter++;
        }
        return connectionList;
    }

    public int getLineCounter() {
        return lineCounter;
    }
}
