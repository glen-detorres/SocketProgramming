package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket =  null;
    private DataOutputStream out = null;
    private static Logger logger = LogManager.getLogger(Client.class);

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            logger.info("Client connected");

            //Send to server
            Input input = new Input(socket);
            input.sendInput();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }

    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

}
