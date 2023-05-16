package cz.tmobile.ibmd.checker;

import java.net.InetAddress;

public class Checker {
    // v Případě že dosáhne větčích hodnot než 2 000 000 000 použít Long
    private Long convertIpAddressToNumber(String ipAddress){
        //případ/podmínka kdy destination server obsahuje celý rozsah IP adres např.: 10.238.0.20 - 10.238.0.30

        String[] output = ipAddress.split("[.]");
        int n0 = Integer.parseInt(output[0]);
        int n1= Integer.parseInt(output[1]);
        int n2= Integer.parseInt(output[2]);
        int n3= Integer.parseInt(output[3]);

        //hlavní čítač převodu na celé číslo
        long ipAddressAsANumber = n3 +n2 * 1000 + n1* 1000000 + n0 * 1000000000;
        return ipAddressAsANumber;
    }

    //Vrací true pokud používá daný proces danou connectionu
    private Boolean compare(Process process, Connection connection) {
        //Hlavní mozek celého porovnání provná dva Stringy
        if (process.getHost().equals(connection.getDestinationServer())) {
            if (process.getPort().equals(connection.getPort())) {
                return true;
            }
        }
        //Porovná případ kdy destination server obsahuje více IP adres odělených znakem |
        if (connection.getDestinationServer().contains("|")){
            // [] = Povolené znaky
            String[] output = connection.getDestinationServer().split("[|]");
            for (String ipAddress : output) {
                if (process.getHost().equals(ipAddress)) {
                    if (process.getPort().equals(connection.getPort())) {
                        return true;
                    }
                }
            }
        }
        //Rozdělení - a rozdělení . od  případu 10.238.0.20 - 10.238.0.30
        if (connection.getDestinationServer().contains(" - ")){
            String[] ipBorder = connection.getDestinationServer().split(" - ");




        }


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
        return  result;
    }
}
