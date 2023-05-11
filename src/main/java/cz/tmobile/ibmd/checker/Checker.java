package cz.tmobile.ibmd.checker;

public class Checker {
    //Vrací true pokud používá daný proces danou connectionu
    private Boolean compare(Process process, Connection connection){
        //Hlavní mozek celého porovnání provná dva Stringy
        if (process.getHost().equals(connection.getDestinationServer())){
            if (process.getPort().equals(connection.getPort())){
                return true;
            }
        }
        //TODO Porovnat případ kdy destination server obsahuje více IP adres odělených znakem |
        //TODO případy kdy destination server obsahuje celý rozsah IP adres např.: 10.238.0.20 - 10.238.0.30
        //TODO případy kdy: 10.238.0.40-50
            //false = Tahle connectiona se nepoužívá v tomhle procesu
        return false;
    }
    public Result check(ProcessList processList, ConnectionList connectionList){
        Result result = new Result();

        for (Connection connection : connectionList.getConnections()) {
            //čítač procesů které využívají danou connectionu
            Integer countprocess = 0;
            for (Process process : processList.getProcesses()) {
                // Používá danný process danou Connection == výsledek porovnání Ano
                if (compare(process, connection) == true){
                    //zvedne se jen tehdy o 1 když daný proces používá danou conectionu
                    countprocess++;
                }


            }
            //Nenašel se ani jeden process který používá tuto connectionu
            if (countprocess == 0){
                result.getRemovedServers().add(connection);
            }
        }
        for (Process process : processList.getProcesses()) {
            //čítač Connectionů které používžá daný process
            Integer countconnection = 0;
            for (Connection connection : connectionList.getConnections()) {
                // Používá danný process danou Connection == výsledek porovnání Ano
                if (compare(process, connection) == true){


                    //zvedne se jen tehdy o 1 když daný proces používá danou conectionu
                    countconnection++;
                }
            }
            //Nenašel se ani jeden connection který používá daný process
            if (countconnection == 0){
                Connection connection = new Connection(" "," ", " ",process.getHost()," "," ", process.getPort());
                result.getMissingServers().add(connection);
                //s
            }
        }
        return null;
    }
}
