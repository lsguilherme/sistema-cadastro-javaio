package service;

import domain.User;

import java.util.Scanner;

import static service.Form.questions;

public class UserService {

    public static void registerUser(){
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
                    user.setAge(sc.nextInt());
                    break;
                case 3:
                    user.setHeight(sc.nextDouble());
                    break;
                default:
                    break;
            }

        }
            System.out.println(user);





        sc.close();
    }
}
