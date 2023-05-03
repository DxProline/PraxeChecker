package cz.tmobile.ibmd.checker;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConnectionListLoader connectionListLoader = new ConnectionListLoader();
        connectionListLoader.load("C:\\Users\\jiric\\Documents\\ahoj.txt");

    }
}