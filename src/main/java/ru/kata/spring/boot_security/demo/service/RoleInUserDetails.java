package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Optional;

@Service
public class RoleInUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public RoleInUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
        if (user == null){
            throw  new UsernameNotFoundException("пользователь не найден");
        }
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRoles());
    }

}
