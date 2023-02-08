package com.example.facultyfeedback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String rollNum;
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(rollNum, that.rollNum) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, rollNum, department);
    }
}
