package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public Socket initConnection(ServerSocket serverSocket) {
        try {
            socket = serverSocket.accept();
            System.out.println("Client accepted");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public void closeConnection(DataOutputStream out) {
        try {
            socket.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(DataInputStream input) {
        try {
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
