package com.example.facultyfeedback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;
    private String userName;
    private String password;

}
