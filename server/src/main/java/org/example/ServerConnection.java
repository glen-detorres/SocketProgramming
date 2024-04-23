package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private static Logger logger = LogManager.getLogger(ServerConnection.class);

    public ServerConnection(Socket socket) {
        this.socket = socket;
    }

    public Socket initConnection(ServerSocket serverSocket) {
        try {
            socket = serverSocket.accept();
            logger.info("Client accepted");
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
