package org.example;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket =  null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            System.out.println("Enter a number: ");

            input = new DataInputStream(System.in);
//            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        String line = "";

        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}
