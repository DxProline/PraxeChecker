package cz.tmobile.ibmd.checker;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
    public void export(Result result, String filename) throws IOException {
        FileWriter myWriter = new FileWriter(filename);


        for (Connection connection : result.getMissingServers()){
            // \n = skok na nový řádek
            myWriter.write("Missing;" +connection.toString()+"\n");
        }

        for (Connection connection : result.getRemovedServers()){
            myWriter.write("Removed;"+ connection.toString()+"\n");
        }

        myWriter.close();
        return;
    }
}
