package ru.kata.spring.boot_security.demo.Dao;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    User show(long id);
    void save(User user);
    void update(long id,User user);
    void delete(long id);
}
