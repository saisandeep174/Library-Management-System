package models;

public class User {
    public int id;
    public String name;
    public String email;
    public String userType;

    public User(String name, String email, String userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }
}
