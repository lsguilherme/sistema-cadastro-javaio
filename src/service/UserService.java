package service;

import domain.User;
import exceptions.InvalidAgeException;
import exceptions.InvalidEmailExecption;
import exceptions.InvalidHeightException;
import exceptions.InvalidNameException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static service.FormService.questions;

public class UserService {

    // Passar o path absoluto da pasta src
    private static String PATH_DIR = "F:\\ws-java\\devmagro\\src\\users";

    private static final ArrayList<User> users = new ArrayList<>();

    public static void registerUser() throws Exception {
        Scanner sc = new Scanner(System.in);

        User user = new User();

        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));


            switch (i){
                case 0:
                    String name = sc.nextLine();
                    if (name.length() < 10){
                        throw new InvalidNameException("O nome deve ter pelo menos 10 caracteres.");
                    }
                    user.setName(name);
                    break;
                case 1:
                    String email = sc.nextLine();

                    if (!email.contains("@")){
                        throw new InvalidEmailExecption("O email está inválido, deve conter '@'.");
                    }

                    if (emailExistsInFiles(email)) {
                        throw new InvalidEmailExecption("O email já está cadastrado.");
                    }

                    user.setEmail(email);
                    break;
                case 2:
                    int age = sc.nextInt();
                    sc.nextLine();
                    if (age < 18){
                        throw new InvalidAgeException("O usuário é menor de idade.");
                    }
                    user.setAge(age);
                    break;
                case 3:
                    String height = sc.nextLine();

                    if (!height.matches("\\d+,\\d+")){
                        throw new InvalidHeightException("A altura deve ser digitado com ','.");
                    }

                    user.setHeight(height);
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

    public static void searchUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome ou parte do nome que deseja buscar: ");
        String searchName = sc.nextLine().toLowerCase();

        File dir = new File(PATH_DIR);
        File[] files = dir.listFiles();

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("O diretório não existe ou não é um diretório válido.");
            return;
        }

        if (files == null || files.length == 0) {
            System.out.println("O diretório está vazio.");
            return;
        }

        boolean found = false;

        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".txt")) {
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String name = br.readLine();
                if (name.toLowerCase().contains(searchName)) {
                    found = true;
                    System.out.println("Cadastrados: " + name);

                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (!found) {
            System.out.println("Nenhum usuário com " + searchName.toUpperCase() + " cadastrado.");
        }
    }

    private static boolean emailExistsInFiles(String email) {
        File dir = new File(PATH_DIR);
        File[] files = dir.listFiles();

        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".txt")) {
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.readLine();
                String fileEmail = br.readLine();
                if (email.equalsIgnoreCase(fileEmail)) {
                    return true;
                }
            } catch (IOException e) {
                System.out.println("Error: " + file.getName());
            }
        }

        return false;
    }

}
