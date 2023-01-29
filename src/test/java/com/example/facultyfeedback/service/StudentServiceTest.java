package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest {

    private StudentService studentService;

    private final StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void shouldReturnAllStudents() {
        Student student = new Student(1L, "Nandini", "Jadhav", "12", null);
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));

        final List<StudentDTO> students = studentService.getStudents();

        StudentDTO expected = new StudentDTO(1L, "Nandini", "Jadhav", "12");
        assertEquals(List.of(expected), students);
    }
}