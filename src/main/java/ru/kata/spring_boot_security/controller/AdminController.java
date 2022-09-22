package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public List<User> printAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/new")
    public User createNewUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.addNewUser(user);
        return user;
    }

    @PutMapping("/edit")
    public User updateUser(@RequestBody User user) {
        User userNotUpdate = service.getUser(user.getId());

        userNotUpdate.setFirstName(user.getFirstName());
        userNotUpdate.setLastName(user.getLastName());
        userNotUpdate.setAge(user.getAge());
        userNotUpdate.setEmail(user.getEmail());
        if (!user.getPassword().equals("")) {
            userNotUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userNotUpdate.setRoles(user.getRoles());
        service.updateUser(userNotUpdate);
        return service.getUser(user.getId());
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        service.userDelete(user.getId());
        return "Юзер c id= " +user.getId()+" удален к едрене-Фене";
    }
}