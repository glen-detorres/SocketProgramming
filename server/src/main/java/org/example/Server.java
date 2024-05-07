package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private static ServerPoemReader serverPoemReader;
    private static Logger logger = LogManager.getLogger(Server.class);
    static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    static String appConfigPath = rootPath + "application.properties";
    static Properties properties = new Properties();
    private String loadingType;

    public Server(int port) {
        try {
            properties.load(new FileInputStream(appConfigPath));

            serverSocket = new ServerSocket(port);
            logger.info("Server started");
            logger.info("Waiting for client...");

            ServerConnection connection = new ServerConnection(socket);
            socket = connection.initConnection(serverSocket);

            loadingType = properties.getProperty("LOADING");
            if (loadingType.equals("EAGER")) {
                serverPoemReader = new ServerPoemReader();
                serverPoemReader.readPoemLines();
            }

            //Get input from client
            ServerInput inputReader = new ServerInput();
            inputReader.readInput(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        properties.load(new FileInputStream(appConfigPath));
        int port = Integer.parseInt(properties.getProperty("PORT"));

        Server server = new Server(port);
    }

}