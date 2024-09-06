package com.example.restapidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("/api")
public class UsersRestApi {
    private final DbUsersConfig dbUsersConfig;

    private List<User> users;

    private void initUsers() {
        users = List.of(
                new User("jd", "John", "Doe", 1),
                new User("jane.smith", "Jane",  "Smith", 2),
                new User("mickey", "Mike", "Johnson", 3)
        );
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
}
