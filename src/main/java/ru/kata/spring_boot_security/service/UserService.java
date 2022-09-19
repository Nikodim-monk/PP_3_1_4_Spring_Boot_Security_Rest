package ru.kata.spring_boot_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring_boot_security.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getByEmail(String email);

    List<User> getAllUsers();

    void addNewUser(User user);

    User getUser(long id);

    void updateUser(User user);

    void userDelete(long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
