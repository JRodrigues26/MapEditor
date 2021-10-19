package org.academiadecodigo.altcatras.classes;

import java.io.*;


public class FileCreator {

    private FileWriter writer;
    private BufferedWriter bWriter;


    public void save(String file, String text) throws IOException {
        writer = new FileWriter(file);
        bWriter = new BufferedWriter(writer);
        bWriter.write(text);
        bWriter.flush();
        bWriter.close();
    }

    public String paste() throws IOException {
        FileReader reader = new FileReader("resources/work");
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        String result = "";

        while ((line = bReader.readLine()) != null) {
            result += line;
        }
        bReader.close();
        return result;
    }

}
