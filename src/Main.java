import static service.Form.loadQuestions;
import static service.UserService.registerUser;

public class Main {
    public static void main(String[] args)  {
        loadQuestions();
        registerUser();
    }

}