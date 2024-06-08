package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService adminService) {
        this.userService = adminService;
    }

    @GetMapping()
    public String index(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("authUser",user);
        model.addAttribute("users", userService.findUserById(user.getId()));
        return "user";
    }

}
