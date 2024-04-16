package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream input = null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for client...");

            initConnection();
            readInput();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }

    private void initConnection() {
        try {
            socket = serverSocket.accept();
            System.out.println("Client accepted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInput() {
        try {
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String str = "";
            while (!str.equals("##")) {
                str = input.readUTF();
                System.out.println("client input: " + str);
                getLineFromPoem(str);
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            socket.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLineFromPoem(String input) {
        int currentLine = 0;
        int poemLineNum = Integer.valueOf(input);
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("poem.txt"));
            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == poemLineNum) {
                    sendResponse(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void sendResponse(String poemLine) {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            pr.println(poemLine);
            pr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
