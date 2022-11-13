package ru.kata.spring_boot_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody User user) {
        service.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        service.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
//        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}