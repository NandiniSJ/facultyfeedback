package com.example.facultyfeedback.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private int number;
    @OneToMany(mappedBy = "semester")
    private List<Subject> subjects;
}
