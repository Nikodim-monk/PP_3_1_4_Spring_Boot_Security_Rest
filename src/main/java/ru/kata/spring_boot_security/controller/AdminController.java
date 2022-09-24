package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.entity.UserExp;
import ru.kata.spring_boot_security.service.UserService;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String printAllUsers(Principal principal, ModelMap model) {
//        model.addAttribute("id", service.getByEmail(principal.getName()).getId());
        model.addAttribute("id", service.getByEmail("lr1975@yandex.ru").getId());
        return "admin_panel";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<User> getAllUser() {
        return service.getAllUsers();
    }

    @PostMapping("/new")
    @ResponseBody
    public UserExp createNewUser(@RequestBody UserExp user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        service.addNewUser(user);
        return user;
    }

//    @PostMapping("/new")
//    public String createNewUser(@ModelAttribute("user") UserExp user,
//                                @RequestParam(value = "role", required = false) String role) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        user.setRoles(roles(role));
////        service.addNewUser(user);
//        return "redirect:/admin";
//    }

    @PutMapping("/edit")
    @ResponseBody
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
    @ResponseBody
    public String deleteUser(@RequestBody User user) {
        service.userDelete(user.getId());
        return "Юзер c id= " +user.getId()+" удален к едрене-Фене";
    }

    public static String roleSting(User elem) {
        String rs = elem.getRoles().toString();
        if (rs.contains("ROLE_ADMIN") && rs.contains("ROLE_USER")) {
            return "ADMIN USER";
        } else if (rs.contains("ROLE_ADMIN")) {
            return "ADMIN";
        } else if (rs.contains("ROLE_USER")) {
            return "USER";
        } else {
            return "";
        }
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