package service;

import domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static service.FormService.questions;

public class UserService {
    // Passar o path absoluto da pasta src
    private static String PATH_DIR = "F:\\ws-java\\devmagro\\src\\users";

    private static ArrayList<User> users = new ArrayList<>();

    public static void registerUser() {
        Scanner sc = new Scanner(System.in);

        User user = new User();

        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));

            switch (i){
                case 0:
                    user.setName(sc.nextLine());
                    break;
                case 1:
                    user.setEmail(sc.nextLine());
                    break;
                case 2:
                    user.setAge(Integer.parseInt(sc.nextLine().trim()));
                    break;
                case 3:
                    user.setHeight(Double.parseDouble(sc.nextLine().replace(",", ".").trim()));
                    break;
                default:
                    break;
            }
        }


        users.add(user);

        createUserFile(user);


    }

    private static void createUserFile(User user){
        File pathDir = new File(PATH_DIR);

        if(!pathDir.exists()){
            pathDir.mkdir();
        }

        long qtdUsers = Arrays.stream(pathDir.listFiles())
                .filter(f -> f.isFile())
                .count();
        String nameArchive = (qtdUsers+1) +"-" + user.getName().toUpperCase().replaceAll("\\s+", "").trim() + ".txt";

        File userFile = new File(pathDir, nameArchive);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(userFile))) {
            bw.write(user.getName());
            bw.newLine();
            bw.write(user.getEmail());
            bw.newLine();
            bw.write(String.valueOf(user.getAge()));
            bw.newLine();
            bw.write(String.valueOf(user.getHeight()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void listUser() {
        File dir = new File(PATH_DIR);
        File[] files = dir.listFiles();

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("O diretório não existe ou não é um diretório válido.");
        }

        if (files == null || files.length == 0) {
            System.out.println("O diretório está vazio.");
        }

        for (File file : files){
            if (!file.isFile() || !file.getName().endsWith(".txt")) {
                continue;
            }

            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String[] number = file.getName().split("-");
                String firstLine = br.readLine();
                System.out.println(number[0] + " - " + firstLine);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }

    }

}
