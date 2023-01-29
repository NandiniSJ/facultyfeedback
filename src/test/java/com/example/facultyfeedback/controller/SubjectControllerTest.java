package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.repositories.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SubjectControllerTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnListOfAllSubjects() throws Exception {
        Subject subject = new Subject(1L, null, "Maths");
        subjectRepository.save(subject);

        this.mockMvc.perform(get("/subject"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(subject.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(subject.getName())));
    }

    @AfterEach
    void tearDown() {
        subjectRepository.deleteAll();
    }
}