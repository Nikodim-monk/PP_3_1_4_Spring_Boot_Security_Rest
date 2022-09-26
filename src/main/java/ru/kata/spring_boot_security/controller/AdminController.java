package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public List<User>  createNewUser(@ModelAttribute("user") User user,
                                 @RequestParam(value = "role", required = false) String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles(role));
        service.addNewUser(user);
        return service.getAllUsers();
    }

    @PutMapping("/edit")
    @ResponseBody
    public List<User>  updateUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role", required = false) String role) {
        User userNotUpdate = service.getUser(user.getId());

        userNotUpdate.setFirstName(user.getFirstName());
        userNotUpdate.setLastName(user.getLastName());
        userNotUpdate.setAge(user.getAge());
        userNotUpdate.setEmail(user.getEmail());
        if (!user.getPassword().equals("")) {
            userNotUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (role != null) {
            userNotUpdate.setRoles(roles(role));
        }
        service.updateUser(userNotUpdate);
        return service.getAllUsers();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public List<User>  deleteUser(@ModelAttribute("user") User user) {
        service.userDelete(user.getId());
        return service.getAllUsers();
    }

    public static Collection<Role> roles(String role){
        Collection<Role> roles = new ArrayList<>();
        switch (role) {
            case "USER" -> roles.add(new Role(1, "ROLE_USER"));
            case "ADMIN" -> roles.add(new Role(2, "ROLE_ADMIN"));
            case "ADMIN USER" -> {
                roles.add(new Role(1, "ROLE_USER"));
                roles.add(new Role(2, "ROLE_ADMIN"));
            }
        }
        return roles;
    }

}