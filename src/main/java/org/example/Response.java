package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Response {
    private InputStreamReader in = null;
    private static Socket socket;
    public Response(Socket socket) {
        this.socket = socket;
    }

    public void readResponse() {
        try {
            in = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String str = br.readLine();
            System.out.println("server response: " + str);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
