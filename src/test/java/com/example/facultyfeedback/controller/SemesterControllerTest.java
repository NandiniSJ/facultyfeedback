package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.repositories.SemesterRepository;
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
class SemesterControllerTest {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnListOfSemester() throws Exception {
        Semester semester = new Semester(1L, 1, null);
        semesterRepository.save(semester);

        this.mockMvc.perform(get("/semester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(semester.getId().intValue())))
                .andExpect(jsonPath("$[0].number", is(semester.getNumber())));
    }

    @AfterEach
    void tearDown() {
        semesterRepository.deleteAll();
    }
}