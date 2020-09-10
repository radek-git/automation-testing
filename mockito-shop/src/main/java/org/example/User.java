package org.example;

public class User {
    enum Type {
        EMPLOYEE, ADMIN
    }

    String login;
    String password;
    Type type;

    public User(String login, String password, Type type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }
}
