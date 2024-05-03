package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerPoemReader {

    private static List<String> poemLines = new ArrayList<>();

    public void readPoemLines() {
        String line = "";
        try {
            URL fileUrl = Server.class.getClassLoader().getResource("Poem.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fileUrl.getFile()));
            while ((line = reader.readLine()) != null) {
                poemLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLineFromPoem(String input) {
        int poemLineNum = Integer.valueOf(input);
        if (poemLineNum < 1 || poemLineNum > poemLines.size()) {
            return "Number out of range!";
        } else {
            String poemLine = poemLines.get(poemLineNum - 1);
            if (!poemLine.isEmpty()) {
                return poemLine;
            } else {
                return "Number " + poemLineNum + " is an empty line!";
            }
        }
    }
}
