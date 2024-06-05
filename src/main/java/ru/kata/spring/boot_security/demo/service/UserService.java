package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;


public interface UserService {
    List<User> getAllUser();
    User show(long id);
    User findByUsername(String username);
    void save(User saveUser);
    void update(long id, User user);
    void delete(long id);
}
