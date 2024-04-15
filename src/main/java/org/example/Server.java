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

            socket = serverSocket.accept();
            System.out.println("Client accepted");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new FileReader("poem.txt"));

            String line = "";

            System.out.println(getLineFromPoem(Integer.valueOf(input.readUTF()), reader));

            while (!line.equals("Over")) {
                try {
                    line = input.readUTF();
                    System.out.println(line);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Closing connection");
            socket.close();
            input.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String args[])
    {
        Server server = new Server(5000);
    }

    private static String getLineFromPoem(int input, BufferedReader reader) {
        int currentLine = 0;
        String line = "";
        String poemLine = "";
        try {
            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == input) {
                    poemLine = line;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return poemLine;
    }
}
