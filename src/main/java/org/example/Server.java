package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for client...");

            Connection connection = new Connection(socket);
            socket = connection.initConnection(serverSocket);

            //Get input from client
            Input inputReader = new Input(socket);
            inputReader.readInput();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }

}
