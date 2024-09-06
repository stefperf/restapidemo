package com.example.restapidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user) {
        boolean useridExists = userRepository.findByUserid(user.getUserid()).isPresent();
        if (useridExists) {
            throw new IllegalArgumentException(String.format("userid '%s' already exists", user.getUserid()));
        }
        return userRepository.save(user);
    }

    // Use readOnly for read operations to optimize performance
    @Transactional(readOnly = true)
    public User findUserByUserid(String userid) {
        return userRepository.findByUserid(userid).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersBySurname(String surname) {
        var usersFound = userRepository.findBySurname(surname);
        return usersFound;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUserid(String userid) {
        return userRepository.findByUserid(userid);
    }

    @Transactional(readOnly = true)
    public List<User> listUsersSortedByCreation() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, User.USER_NUMBER));
    }

    @Transactional(readOnly = true)
    public List<User> listUsersSortedByUserid() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, User.USERID));
    }

    @Transactional(readOnly = true)
    public List<User> listUsersSortedBySurnameAndName() {
        return userRepository.findAll(Sort.by(Sort.Order.asc(User.SURNAME), Sort.Order.asc(User.NAME)));
    }

    @Transactional(readOnly = true)
    public List<User> listUsersSortedByNameAndSurname() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, User.NAME, User.SURNAME));
    }

    @Transactional
    public void saveAll(List<User> userList) {
        userRepository.saveAll(userList);
    }
}

