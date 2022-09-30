package ru.kata.spring_boot_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService service;

    @GetMapping()
    public String printAllUsers() {
        return "admin_panel";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<User> getAllUser() {
        return service.getAllUsers();
    }

    @PostMapping("/new")
    @ResponseBody
    public void createNewUser(@RequestBody User user) {
        service.addNewUser(user);
    }

    @PutMapping("/edit")
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        service.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        service.deleteUser(id);
    }
}