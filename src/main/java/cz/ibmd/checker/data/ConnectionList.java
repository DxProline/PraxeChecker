package cz.ibmd.checker.data;

import java.util.ArrayList;

public class ConnectionList {
    private ArrayList<Connection> connections = new ArrayList<>();

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }
}