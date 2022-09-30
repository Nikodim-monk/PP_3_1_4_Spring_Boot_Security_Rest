package ru.kata.spring_boot_security.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private int age;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) && email.equals(user.email) &&
                password.equals(user.password) && roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, password, roles);
    }
}
