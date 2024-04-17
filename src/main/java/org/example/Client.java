package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Client {
    private Socket socket =  null;
    private DataOutputStream out = null;
    private InputStreamReader in = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Client connected");

            //Send to server
            sendInput();
            closeConnection();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

    private void sendInput() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            String line = "";
            while (!line.equals("end")) {
                System.out.println("Enter a number: ");
                line = input.nextLine();
                if (!line.equals("end")) {
                    out.writeUTF(line);
                    //Response from server
                    readResponse();
                }
            }
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
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            socket.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
