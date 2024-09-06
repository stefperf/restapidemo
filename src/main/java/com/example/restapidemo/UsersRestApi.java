package com.example.restapidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("/api")
public class UsersRestApi {
    private final DbUsersConfig dbUsersConfig;

    private ArrayList<User> users;

    private void initUsers() {
        users = new ArrayList<>(List.of(
                new User("jd", "John", "Doe", 1),
                new User("jane.smith", "Jane", "Smith", 2),
                new User("mickey", "Mike", "Johnson", 3)
        ));
    }

    @Autowired
    public UsersRestApi(DbUsersConfig dbUsersConfig) {
        this.dbUsersConfig = dbUsersConfig;
        initUsers();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, from the REST API!";
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return users;
    }

    @GetMapping("/users/byuserid")
    public Optional<User> getUserBySurname(String userid) {
        return users.stream().filter(user -> user.userid().equals(userid)).findFirst();
    }

    @GetMapping("/meta/dbname")
    public String getDbName() {
        return dbUsersConfig.dbname();
    }

    // Test like this for success insertion:
    // curl -X POST http://localhost:8080/api/users/add -H "Content-Type: application/json" -d "{\"userid\": \"newuser\", \"name\": \"New\", \"surname\": \"User\"}"
    //
    // Test like this for failure due to existing user:
    // curl -X POST http://localhost:8080/api/users/add -H "Content-Type: application/json" -d "{\"userid\": \"jd\", \"name\": \"New\", \"surname\": \"User\"}"
    //
    @PostMapping("/users/add")
    public String addUser(@RequestBody User newUser) {
        // Check if a user with the same userid already exists
        boolean userExists = users.stream().anyMatch(user -> user.userid().equals(newUser.userid()));

        // If the userid exists, throw an exception
        if (userExists) {
            throw new IllegalArgumentException(String.format("User with userid '%s' already exists!", newUser.userid()));
        }

        int newUserNumber = users.getLast().userNumber() + 1;
        users.add(newUser.withUserNumber(newUserNumber));
        return String.format("User of userid '%s' added successfully with new userNumber = %d", newUser.userid(), newUserNumber);
    }
}
