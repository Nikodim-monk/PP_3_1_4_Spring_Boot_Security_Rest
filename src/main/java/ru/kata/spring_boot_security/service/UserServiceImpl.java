package ru.kata.spring_boot_security.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository,@Lazy PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User getUser(long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateUser(User user) {
        User userNotUpdate = getUser(user.getId());
        userNotUpdate.setFirstName(user.getFirstName());
        userNotUpdate.setLastName(user.getLastName());
        userNotUpdate.setAge(user.getAge());
        userNotUpdate.setEmail(user.getEmail());
        userNotUpdate.setRoles(user.getRoles());
        if (!user.getPassword().equals("")) {
            userNotUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        repository.saveAndFlush(userNotUpdate);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }
}
