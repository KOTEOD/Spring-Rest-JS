package ru.kata.spring.boot_security.demo.rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/rest/user")
public class RestUserController {
    UserService userService;

    @PostMapping
    public ResponseEntity<User> show(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
