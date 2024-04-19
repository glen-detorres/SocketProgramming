package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class PoemReader {
    private static Response response = null;

    public PoemReader(Socket socket) {
        response = new Response(socket);
    }
    public void getLineFromPoem(String input) {
        int currentLine = 0;
        int poemLineNum = Integer.valueOf(input);
        String line = "";
        try {
            URL fileUrl = Server.class.getClassLoader().getResource("Poem.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fileUrl.getFile()));

            if (poemLineNum > reader.lines().count() || poemLineNum < 1) {
                response.sendResponse("Number out of range!");
            } else {
                BufferedReader newReader = new BufferedReader(new FileReader(fileUrl.getFile()));
                while ((line = newReader.readLine()) != null) {
                    currentLine++;
                    if (currentLine == poemLineNum) {
                        if (line.isEmpty()) {
                            response.sendResponse("Number " + poemLineNum + " is an empty line!");
                        } else {
                            response.sendResponse(line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
