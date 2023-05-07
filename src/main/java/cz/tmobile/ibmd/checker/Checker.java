package cz.tmobile.ibmd.checker;

public class Checker {
    public Result check(ProcessList processList, ConnectionList connectionList){
        Result result = new Result();

        for (Connection connection : connectionList.getConnections()) {
            for (Process process : processList.getProcesses()) {

            }

        }
        return null;
    }

}
