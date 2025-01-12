package service;

import java.util.Scanner;

import static service.FormService.*;
import static service.UserService.*;

public class MenuService {

    public static void showMenu() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                1 - Cadastrar o usuário
                2 - Listar todos usuários cadastrados
                3 - Cadastrar nova pergunta no formulário
                4 - Deletar pergunta do formulário
                5 - Pesquisar usuário por nome ou idade ou email
                """);

        int choice = sc.nextInt();

        switch (choice){
            case 1:
                loadQuestions();
                registerUser();
                break;
            case 2:
                listUser();
                break;
            case 3:
                loadQuestions();
                addQuestion();
                break;
            case 4:
                loadQuestions();
                deleteQuestion();
                break;
            case 5:
                searchUser();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        sc.close();
    }
}
