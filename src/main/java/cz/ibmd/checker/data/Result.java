package cz.ibmd.checker.data;

import java.util.ArrayList;

public class Result {
    private ArrayList<Connection> missingServers = new ArrayList<>();
    private ArrayList<Connection> removedServers = new ArrayList<>();

    public ArrayList<Connection> getMissingServers() {
        return missingServers;
    }

    public ArrayList<Connection> getRemovedServers() {
        return removedServers;
    }
}
