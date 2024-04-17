package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket =  null;
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Client connected");

            //Send to server
            Input input = new Input(socket);
            input.sendInput();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

}
