package com.example.restapidemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by their userid (which is unique)
    Optional<User> findByUserid(String userid);

    // Custom method to find a user by their name (which is NOT unique)
    List<User> findByName(String name);

    // Custom method to find a user by their surname (which is NOT unique)
    List<User> findBySurname(String surname);
}
