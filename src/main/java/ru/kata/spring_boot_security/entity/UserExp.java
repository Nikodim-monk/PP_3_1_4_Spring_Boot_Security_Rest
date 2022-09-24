package ru.kata.spring_boot_security.entity;

import lombok.Data;

@Data
public class UserExp {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private String role;
}
//{  "firstName": "Иван","lastName": "Иванов","age": 61,"email": "iva@mail.ru","password": "admin",
//        "roles": [{"id": 1,"role": "ROLE_USER"},{"id": 2,"role": "ROLE_ADMIN"}]  }