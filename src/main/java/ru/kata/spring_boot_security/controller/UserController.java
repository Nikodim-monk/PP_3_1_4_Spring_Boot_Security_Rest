package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping()
    public String printUser(){
        return "user_panel";
    }

    @GetMapping("/principal")
    @ResponseBody
    public User userPrincipal(Principal principal) {
        return service.getByEmail(principal.getName());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User userFor(@PathVariable("id") long id) {
        return service.getUser(id);
    }

}
