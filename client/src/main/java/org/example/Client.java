package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {
    private Socket socket =  null;
    private static Logger logger = LogManager.getLogger(Client.class);

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            logger.info("Client connected");

            //Send to server
            ClientInput input = new ClientInput(socket);
            input.sendInput();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }

    }

    public static void main(String args[]) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));

        int port = Integer.parseInt(properties.getProperty("PORT"));
        String address = properties.getProperty("ADDRESS");

        Client client = new Client(address, port);
    }

}
