package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    private static Socket socket;
    private static PoemReader poemReader;
    private static Response response;
    private static Connection connection;
    private DataOutputStream out = null;
    private static Logger logger = LogManager.getLogger(Input.class);

    public Input(Socket socket) {
        this.socket = socket;
        poemReader = new PoemReader();
        response = new Response(socket);
        connection = new Connection(socket);
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
                    response.readResponse();
                }
            }
            connection.closeConnection(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readInput() {
        try {
            DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String str = "";

            while (!str.equals("end")) {
                str = input.readUTF();
                if (inputValidation(str)) {
                    logger.info("client input: " + str);
                    String poemLine = poemReader.getLineFromPoem(str);
                    response.sendResponse(poemLine);
                } else {
                    response.sendResponse("Enter a valid number!");
                }
            }
            connection.closeConnection(input);
        } catch (IOException e) {
            logger.info("Closing connection");
        }
    }

    private static boolean inputValidation(String input) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(input).matches();
    }
}
