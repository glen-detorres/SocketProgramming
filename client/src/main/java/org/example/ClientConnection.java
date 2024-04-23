package org.example;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public void closeConnection(DataOutputStream out) {
        try {
            socket.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
