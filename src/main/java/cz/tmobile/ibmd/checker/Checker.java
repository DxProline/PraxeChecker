package cz.tmobile.ibmd.checker;
public class Checker {
    // v Případě že dosáhne větčích hodnot než 2 000 000 000 použít Long
    protected Long convertIpAddressToNumber(String ipAddress){
        //případ/podmínka kdy destination server obsahuje celý rozsah IP adres např.: 10.238.0.20 - 10.238.0.30

        String[] output = ipAddress.split("[.]");
        Long n0 = Long.parseLong(output[0]);
        Long n1 = Long.parseLong(output[1]);
        Long n2 = Long.parseLong(output[2]);
        Long n3 = Long.parseLong(output[3]);

        //hlavní čítač převodu na celé číslo
        //Např: 10.238.0.20 --> 10 * 100000000 + 238 * 1000000 + 0 * 1000 + 20 = 010238000020
        long ipAddressAsANumber = n0 * 1000000000 + n1* 1000000 + n2 * 1000 + n3;
        return ipAddressAsANumber;
    }

    //Vrací true pokud používá daný proces danou connectionu
    protected Boolean compare(Process process, Connection connection) {
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
        try {
            //Rozdělení - a rozdělení . od  případu 10.238.0.20 - 10.238.0.30
            if (connection.getDestinationServer().contains(" - ")){
                String[] ipBorder = connection.getDestinationServer().split(" - ");

                //Určení minimální hranice
                long min = convertIpAddressToNumber(ipBorder[0]);
                //Určení maximální hranice
                long max = convertIpAddressToNumber(ipBorder[1]);
                //Převedení IP adresy procesu na číslo
                long ipAddressProcess = convertIpAddressToNumber(process.getHost());
                //Určení zda IP adresa procesu leží někde mezi dolní a horní hranicí
                if (ipAddressProcess >= min && ipAddressProcess <= max) {
                    //Kotrola portů
                    if (process.getPort().equals(connection.getPort())) {
                        return true;
                    }
                }
                return false;
            }


            //případy kdy: 10.238.0.40-50
            // 10.238.0.40 - 50 => 10.238.0.40 - 10.238.0.50
            if (connection.getDestinationServer().contains("-")){
                String[] ipBorder = connection.getDestinationServer().split("-");

                String[] output = ipBorder[0].split("[.]");
                //Určení minimální hranice
                long min = convertIpAddressToNumber(ipBorder[0]);
                //Určení maximální hranice
                long max = convertIpAddressToNumber(output[0]+ "." + output[1] + "." + output[2] + "." + ipBorder[1]);
                //Převedení IP adresy procesu na číslo
                long ipAddressProcess = convertIpAddressToNumber(process.getHost());
                //Určení zda IP adresa procesu leží někde mezi dolní a horní hranicí
                if (ipAddressProcess >= min && ipAddressProcess <= max){
                    //Kotrola portů
                    if (process.getPort().equals(connection.getPort())) {
                        return true;
                    }
                }
            }
            //zachytí úplně všechny chyby, ke kterým může dojít při převodu IP adresy na číslo
            //Například: Když se neskládá ze 4 čísel nebo pokud obsahuje místo číslic písmena
        } catch (Exception exception){
            //Je úplně jedno která z těch IP adres je špatná, connection se musí brát jako nepoužívaná
            return false;
        }




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
                Connection connection = new Connection("","", "",process.getHost(),"","", process.getPort());
                result.getMissingServers().add(connection);
                //s
            }
        }
        return  result;
    }
}
