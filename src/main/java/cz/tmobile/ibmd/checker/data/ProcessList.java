package cz.tmobile.ibmd.checker.data;

import cz.tmobile.ibmd.checker.data.Process;

import java.util.ArrayList;
import java.util.List;

public class ProcessList {
    private List<Process> processes = new ArrayList<>();

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }
}
