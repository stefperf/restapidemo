package com.example.restapidemo;

public record User(String userid, String name, String surname, int userNumber) {
    // Constructor with default value for userNumber
    public User(String userid, String name, String surname) {
        this(userid, name, surname, -1); // Default value for userNumber is -1 for users not stored yet
    }

    User withUserNumber(int userNumber) {
        return new User(userid, name, surname, userNumber);
    }
}
