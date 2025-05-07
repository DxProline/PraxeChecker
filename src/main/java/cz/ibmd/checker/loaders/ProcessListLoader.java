package cz.ibmd.checker.loaders;

import cz.ibmd.checker.data.Process;
import cz.ibmd.checker.data.ProcessList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessListLoader {
    private int lineCounter;
    // Funkce která načítá soubor pomocí BufferedReadru
    public ProcessList load(String filename) throws IOException {
        BufferedReader reader;
        ProcessList processList = new ProcessList();
        // Vytvořen objekt BufferedReader
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        lineCounter++;

        //Null = dokud nedosáhne konec souboru
        while (line != null) {
            //Rozdělí řádek načtený ze souboru na sloupce oddělené středníkem
            String[] output = line.split(";");
            processList.getProcesses().add(new Process(output[0], output[1], output[2]));
            // přečte další řádek
            line = reader.readLine();
            lineCounter++;
        }
        return processList;
    }

    public int getLineCounter() {
        return lineCounter;
    }
}
