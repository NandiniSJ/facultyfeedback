package com.example.facultyfeedback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {

    private Long id;
    private int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemesterDTO that = (SemesterDTO) o;
        return number == that.number && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}
