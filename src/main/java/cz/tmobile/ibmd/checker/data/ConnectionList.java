package cz.tmobile.ibmd.checker.data;

import cz.tmobile.ibmd.checker.data.Connection;

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