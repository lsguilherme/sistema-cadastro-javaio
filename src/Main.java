import static service.FormService.loadQuestions;
import static service.UserService.*;

public class Main {
    public static void main(String[] args)  {
        loadQuestions();
        registerUser();
    }

}