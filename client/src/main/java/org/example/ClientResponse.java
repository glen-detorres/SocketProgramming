package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientResponse {
    private InputStreamReader in = null;
    private static Socket socket;
    private static Logger logger = LogManager.getLogger(ClientResponse.class);

    public ClientResponse(Socket socket) {
        this.socket = socket;
    }

    public void readResponse() {
        try {
            in = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String str = br.readLine();
            logger.info("server response: " + str);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
