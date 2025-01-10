package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Form {
    public static void loadQuestions() {
        String PATH_QUESTIONS = "formulario2.txt";

        ArrayList<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_QUESTIONS))) {
            String line = br.readLine();
            while (line != null) {
                questions.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

        for (String question : questions) {
            System.out.println(question);
        }

    }
}
