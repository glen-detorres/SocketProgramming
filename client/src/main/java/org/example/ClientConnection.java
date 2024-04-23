package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private static Logger logger = LogManager.getLogger(ClientConnection.class);

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
