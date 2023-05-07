package cz.tmobile.ibmd.checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessListLoader {
    // Funkce která načítá soubor pomocí BufferedReadru
    public ProcessList load(String filename) throws IOException {
        BufferedReader reader;
        ProcessList processList = new ProcessList();
        // Vytvořen objekt BufferedReader
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();

        //Null = dokud nedosáhne konec souboru
        while (line != null) {
            //Rozdělí řádek načtený ze souboru na sloupce oddělené středníkem
            String[] output = line.split(";");
            int port = Integer.parseInt(output[2]);
            processList.getProcesses().add(new Process(output[0], output[1], port));
            // přečte další řádek
            line = reader.readLine();
    }
        return processList;
    }
}
