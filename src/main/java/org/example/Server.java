package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.regex.Pattern;

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
            closeConnection();

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

            while (!str.equals("end")) {
                str = input.readUTF();
                System.out.println("STR " + str);
                if (inputValidation(str)) {
                    System.out.println("client input: " + str);
                    getLineFromPoem(str);
                } else {
                    if (!str.equals("end")) {
                        sendResponse();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLineFromPoem(String input) {
        int currentLine = 0;
        int poemLineNum = Integer.valueOf(input);
        String line = "";
        try {
            URL fileUrl = Server.class.getClassLoader().getResource("Poem.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fileUrl.getFile()));
            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == poemLineNum) {
                    sendResponse(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    private void sendResponse() {
        try {
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            pr.println("Enter a valid number!");
            pr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean inputValidation(String input) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(input).matches();
    }
}
