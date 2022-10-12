package ru.kata.spring_boot_security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.RoleService;
import ru.kata.spring_boot_security.service.UserService;

@Component
@RequiredArgsConstructor
public class InitUser {
    private final UserService userService;
    private final RoleService roleService;

    public void createDefaultUsersAndRolesInBase() {
        roleService.addNewRole(new Role("ROLE_USER"));
        roleService.addNewRole(new Role("ROLE_ADMIN"));
        userService.addNewUser(new User("user", "user", 80, "user@mail.ru",
                "user", roleService.getSetRoles("USER")));
        userService.addNewUser(new User("admin", "admin", 90, "admin@mail.ru",
                "admin", roleService.getSetRoles("ADMIN")));
    }
}

