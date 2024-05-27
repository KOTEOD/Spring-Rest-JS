package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUser();
    User show(long s);
    void save(User user);
    void update(long id, User user);
    void delete(long id);
}
