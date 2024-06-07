package ru.kata.spring.boot_security.demo.rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest/admin")
public class RestUserAndRoleController {
    private final UserService userService;

    public RestUserAndRoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> showUserы(){
        List<User> showUser = userService.getAllUser();
        return new ResponseEntity<>(showUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id){
         User user = userService.show(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> save(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestParam("id") int id, @RequestBody User user){
        userService.update(id,user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.delete(id);
    }

}
