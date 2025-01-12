package domain;

public class User {
    private String name;
    private String email;
    private Integer age;
    private String height;

    public User() {
    }

    public User(String name, String email, Integer age, String height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "DADOS CADASTRADOS \n" +
                "---------------------------------\n" +
                "Nome: " + name + '\n' +
                "Email: " + email + '\n' +
                "Idade: " + age + "\n"+
                "Altura: " + height ;
    }
}
