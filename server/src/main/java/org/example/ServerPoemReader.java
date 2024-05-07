package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerPoemReader {
    static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    static String appConfigPath = rootPath + "application.properties";
    static Properties properties = new Properties();

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
        if (getLoadingType().equals("LAZY")) {
            if (poemLines.isEmpty()) {
                readPoemLines();
            }
        }

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

    String getLoadingType() {
        String loadingType = "";
        try {
            properties.load(new FileInputStream(appConfigPath));
            loadingType = properties.getProperty("LOADING");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadingType;
    }
}
