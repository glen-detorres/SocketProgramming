package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;
public class ServerInput {
    private static Socket socket;
    private static ServerPoemReader serverPoemReader;
    private static ServerResponse serverResponse;
    private static ServerConnection serverConnection;
    private DataOutputStream out = null;
    private static Logger logger = LogManager.getLogger(ServerInput.class);

    public ServerInput(Socket socket) {
        this.socket = socket;
        serverPoemReader = new ServerPoemReader();
        serverResponse = new ServerResponse(socket);
        serverConnection = new ServerConnection(socket);
    }

    public void readInput() {
        try {
            DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String str = "";

            while (!str.equals("end")) {
                str = input.readUTF();
                if (inputValidation(str)) {
                    logger.info("client input: " + str);
                    String poemLine = serverPoemReader.getLineFromPoem(str);
                    serverResponse.sendResponse(poemLine);
                } else {
                    serverResponse.sendResponse("Enter a valid number!");
                }
            }
            serverConnection.closeConnection(input);
        } catch (IOException e) {
            logger.info("Closing connection");
        }
    }

    private static boolean inputValidation(String input) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(input).matches();
    }
}
