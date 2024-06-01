package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();
    Role show(long s);
    void save(Role role);
    void update(Role role, Long id);
    void delete(long id);
}
