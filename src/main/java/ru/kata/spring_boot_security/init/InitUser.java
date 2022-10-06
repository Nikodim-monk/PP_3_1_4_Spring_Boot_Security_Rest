package ru.kata.spring_boot_security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitUser {
    private final UserService service;

    public void createDefaultUsersInBase() {
        service.addNewUser(new User(
                "User", "Userov", 80, "user@mail.ru", "user",roles("USER")));
        service.addNewUser(new User(
                "Admin", "Adminov", 90, "admin@mail.ru", "admin", roles("ADMIN")));
        service.addNewUser(new User(
                "AdminUser", "AdminUserov", 100, "adminUser@mail.ru", "admin",
                roles("ADMIN USER")));
    }
    private Set<Role> roles(String role) {
        Set<Role> roles = new HashSet<>();
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

