package com.example.restapidemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;

    // Constructor injection for the UserService
    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize the database with some users
        User user1 = new User("jd", "John", "Doe");
        User user2 = new User("jane.doe", "Jane", "Doe");
        User user3 = new User("alice_smith", "Alice", "Smith");
        User user4 = new User("johnsquared", "John", "Johnson");

        // Save users to the database
        userService.saveAll(List.of(user1, user2, user3, user4));

        // Log output to verify
        System.out.println("Initialized database with sample users.");
    }
}

