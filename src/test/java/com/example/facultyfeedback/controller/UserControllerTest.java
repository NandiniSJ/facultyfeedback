package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.entity.User;
import com.example.facultyfeedback.repositories.StudentRepository;
import com.example.facultyfeedback.repositories.UserRepository;
import com.example.facultyfeedback.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturnStudentByRollNum() throws Exception{
        Student student = new Student(null, "Nandini", "Jadhav", "12","civil", null);
        User user = new User(1L, student.getRollNum(),"n@78880");

        studentRepository.save(student);
        userRepository.save(user);

        this.mockMvc.perform(get("/student").with(httpBasic("12","n@78880")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(student.getLastName())))
                .andExpect(jsonPath("$[0].rollNum", is(student.getRollNum())))
                .andExpect(jsonPath("$[0].department", is(student.getDepartment())));


    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
        userRepository.deleteAll();
    }
}