package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientInput {
    private static Socket socket;
    private static ClientResponse clientResponse;
    private static ClientConnection clientConnection;
    private DataOutputStream out = null;
    private static Logger logger = LogManager.getLogger(ClientInput.class);

    public ClientInput(Socket socket) {
        this.socket = socket;
        clientResponse = new ClientResponse(socket);
        clientConnection = new ClientConnection(socket);
    }

    public void sendInput() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            String line = "";
            while (!line.equals("end")) {
                logger.info("Enter a number (Enter end to stop): ");
                line = input.nextLine();
                if (!line.equals("end")) {
                    out.writeUTF(line);
                    //Response from server
                    clientResponse.readResponse();
                }
            }
            clientConnection.closeConnection(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
