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
            //false = Tahle connectiona se nepoužívá v tomhle procesu
        return false;
    }
    public Result check(ProcessList processList, ConnectionList connectionList){
        Result result = new Result();

        for (Connection connection : connectionList.getConnections()) {
            for (Process process : processList.getProcesses()) {
                // Používá danný process danou Connection == výsledek porovnání Ano
                if (compare(process, connection) == true){
                    System.out.println(connection.getServerName() + " " + process.getProcessName());
                }


            }

        }
        return null;
    }
}
