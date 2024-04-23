package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerResponse {
    private InputStreamReader in = null;
    private static Socket socket;
    private static Logger logger = LogManager.getLogger(ServerResponse.class);

    public ServerResponse(Socket socket) {
        this.socket = socket;
    }

    public void sendResponse(String response) {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            pr.println(response);
            pr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
