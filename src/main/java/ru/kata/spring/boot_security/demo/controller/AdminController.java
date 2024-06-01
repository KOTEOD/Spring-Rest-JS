package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.configs.SuccessUserHandler;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showUser(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "show";
    }

    @GetMapping("/user")
    public String index(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "index";
    }

    @GetMapping("/new")
    public String newAdmin(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        // Здесь roleService должен быть вашим сервисом, предоставляющим все роли
        return "new";
    }

    @PostMapping("/new")
    public String createNewAdmin(@ModelAttribute("user") User user) {
        userService.save(user);
        return "index";
    }

    @GetMapping("/edit")
    public String edit(Model model,@RequestParam("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
