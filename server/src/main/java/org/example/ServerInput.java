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
    private static ServerPoemReader serverPoemReader;
    private static ServerResponse serverResponse;
    private static ServerConnection serverConnection;
    private static Logger logger = LogManager.getLogger(ServerInput.class);

    public ServerInput() {
        serverPoemReader = new ServerPoemReader();

    }

    public void readInput(Socket socket) {
        try {
            serverResponse = new ServerResponse(socket);
            serverConnection = new ServerConnection(socket);
            DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String str = "";

            while (!str.equals("end")) {
                str = input.readUTF();
                processInput(str);
            }

            serverConnection.closeConnection(input);
        } catch (IOException e) {
            logger.info("Closing connection");
        }
    }

    private void processInput(String input) {
        if (validate(input)) {
            logger.info("client input: " + input);
            String poemLine = serverPoemReader.getLineFromPoem(input);
            serverResponse.sendResponse(poemLine);
        } else {
            serverResponse.sendResponse("Enter a valid number!");
        }
    }

    boolean validate(String input) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(input).matches();
    }
}
