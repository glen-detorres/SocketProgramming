package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket =  null;
    private DataOutputStream out = null;
    private InputStreamReader in = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Client connected");

            //Send to server
            sendMessage();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

    private void sendMessage() {
        System.out.println("Enter a number: ");
        try {
            out = new DataOutputStream(socket.getOutputStream());
            Scanner inS = new Scanner(System.in);
            while (!inS.equals("##")) {
                String line = inS.nextLine();
                out.writeUTF(line);
                //Response from server
                readResponse();
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readResponse() {
        try {
            in = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String str = br.readLine();
            System.out.println("server: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
