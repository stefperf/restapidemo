package com.example.restapidemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "app_user") // attention: the table has a different name
public class User {
    // Field name constants to avoid hardcoding
    public static final String USER_NUMBER = "userNumber";
    public static final String USERID = "userid";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNumber;  // Auto-incremented primary key

    @Column(unique = true, nullable = false)
    private String userid;   // Unique and non-nullable column for userid

    @Column(nullable = false)
    private String name;     // Non-nullable column for name

    @Column(nullable = false)
    private String surname;  // Non-nullable column for surname

    // Default constructor (required by JPA)
    public User() {
    }

    // Constructor with all fields except userNumber
    public User(String userid, String name, String surname) {
        this.userid = userid;
        this.name = name;
        this.surname = surname;
    }

    // Constructor with all fields
    public User(int userNumber, String userid, String name, String surname) {
        this.userNumber = userNumber;
        this.userid = userid;
        this.name = name;
        this.surname = surname;
    }

    // Getters and setters
    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Method to update userNumber and return a new instance
    public User withUserNumber(int userNumber) {
        return new User(userNumber, this.userid, this.name, this.surname);
    }

    public String getSurnameName() {
        return String.format("%s, %s", surname, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "userNumber=" + userNumber +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
