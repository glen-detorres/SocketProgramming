package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int inputLine = scanner.nextInt();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("poem.txt"));
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (inputLine == currentLine) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}