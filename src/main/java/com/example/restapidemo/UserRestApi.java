package com.example.restapidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("/api")
public class UserRestApi {
    private final UserService userService;

    private final SomeSillyConfiguration someSillyConfiguration;

    @Autowired
    public UserRestApi(SomeSillyConfiguration someSillyConfiguration, UserService userService) {
        this.userService = userService;
        this.someSillyConfiguration = someSillyConfiguration;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, from the REST API!";
    }

    @GetMapping("/users/sortedbycreation")
    public List<User> listUsersByCreation() {
        return userService.listUsersSortedByCreation();
    }

    @GetMapping("/users/sortedbysurnameandname")
    public List<User> listUsersAlphabetically() {
        return userService.listUsersSortedBySurnameAndName();
    }

    @GetMapping("/users/byuserid")
    public Optional<User> getUserByUserid(@RequestParam String userid) {
        return userService.findByUserid(userid);
    }

    @GetMapping("/users/byname")
    public List<User> getUserByName(@RequestParam String name) {
        return userService.findUsersByName(name);
    }

    @GetMapping("/users/bysurname")
    public List<User> getUserBySurname(@RequestParam String surname) {
        return userService.findUsersBySurname(surname);
    }

    @GetMapping("/meta/currentconfig")
    public String getCurrentConfigAsString() {
        return someSillyConfiguration.toString();
    }

    // Test like this for success insertion:
    // curl -X POST http://localhost:8080/api/users/add -H "Content-Type: application/json" -d "{\"userid\": \"newuser\", \"name\": \"New\", \"surname\": \"User\"}"
    //
    // Test like this for failure due to existing user:
    // curl -X POST http://localhost:8080/api/users/add -H "Content-Type: application/json" -d "{\"userid\": \"jd\", \"name\": \"New\", \"surname\": \"User\"}"
    //
    @PostMapping("/users/add")
    public String addUser(@RequestBody User newUser) {
        try {
            var userWithNewUserNumber = userService.createUser(newUser);
            return "User added successfully with userNumber = " + userWithNewUserNumber.getUserNumber();
        } catch (IllegalArgumentException e) {
            return "Error adding user: " + e.getMessage();
        }
    }
}
