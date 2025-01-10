package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        String PATH_QUESTIONS = "formulario.txt";

        ArrayList<String> questions = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        try(BufferedReader br = new BufferedReader(new FileReader(PATH_QUESTIONS))) {
            String line = br.readLine();
            while (line != null){
                questions.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String question : questions){
            System.out.println(question);
        }
        sc.close();
    }


}