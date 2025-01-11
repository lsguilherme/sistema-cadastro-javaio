package service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FormService {
    private static String PATH_QUESTIONS = "formulario.txt";

    protected static ArrayList<String> questions = new ArrayList<>();

    private static Scanner sc = new Scanner(System.in);

    public static void loadQuestions() {
        questions.clear();
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

    public static void addQuestion() {


        int nextLineNumber = questions.size() + 1;

        System.out.println("Digite uma nova pergunta(Ex: Qual seu peso?)");
        String newQuestion = sc.nextLine();

        sc.close();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_QUESTIONS, true))){
            bw.newLine();
            bw.write(nextLineNumber + " - " + newQuestion);
            System.out.println("Nova pergunta cadastrada: " + nextLineNumber + " - " + newQuestion);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }


    }

    public static void deleteQuestion(){

        System.out.println("-------------------------------------------");
        for(String line : questions){
            System.out.println(line);
        }
        System.out.println("-------------------------------------------");
        System.out.println("Qual pergunta você gostaria de deletar?");
        int questionRemove = sc.nextInt();
        sc.nextLine();

        if (questionRemove <= 4){
            System.out.println("Não é possível remover essas perguntas!");
        } else {
            questions.remove(questionRemove - 1);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_QUESTIONS))){
            for (int i = 0; i < questions.size(); i++) {
                String updatedQuestion = (i + 1) + " - " + questions.get(i).substring(3).trim();

                bw.write(updatedQuestion);
                bw.newLine();

            }

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        loadQuestions();

        System.out.println("------------ Lista Atualizada ------------");

        for(String line : questions){
            System.out.println(line);
        }
        System.out.println("-------------------------------------------");
        sc.close();
    }


}
