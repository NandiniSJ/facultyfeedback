package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldReturnListOfStudents() throws Exception {
        Student student = new Student(1L, "Nandini", "Jadhav", "12", null);
        studentRepository.save(student);

        this.mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(student.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(student.getLastName())))
                .andExpect(jsonPath("$[0].rollNum", is(student.getRollNum())));
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }
}