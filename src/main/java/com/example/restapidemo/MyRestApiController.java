package com.example.restapidemo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
@RequestMapping("/api")
public class MyRestApiController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, from the REST API!";
    }

    @GetMapping("/persons")
    public List<Person> getPersonList() {
        return List.of(
                new Person("John", "Doe", 30),
                new Person("Jane",  "Smith", 25),
                new Person("Mike", "Johnson", 28)
        );
    }
}
