package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Form {
    private static String PATH_QUESTIONS = "formulario.txt";

    protected static ArrayList<String> questions = new ArrayList<>();

    public static void loadQuestions() {

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_QUESTIONS))) {
            String line = br.readLine();
            while (line != null) {
                questions.add(line);
                 line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }




}
