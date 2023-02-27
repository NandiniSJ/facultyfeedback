package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.User;
import com.example.facultyfeedback.repositories.SemesterRepository;
import com.example.facultyfeedback.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SemesterControllerTest {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnListOfSemester() throws Exception {
        Semester semester = new Semester(1L, 1, null);
        semesterRepository.save(semester);
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);

        this.mockMvc.perform(get("/semester").with(httpBasic("12","n@78880")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number", is(semester.getNumber())));
    }

    @Test
    void shouldSaveSemester() throws Exception {
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);
        String body = "{\n" +
                "    \"number\": 1\n" +
                "}";
        this.mockMvc.perform(post("/semester").with(httpBasic("12","n@78880"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
        final List<Semester> semesters = semesterRepository.findAll();
        assertEquals(1, semesters.size());
        assertEquals(1, semesters.get(0).getNumber());
    }

    @AfterEach
    void tearDown() {
        semesterRepository.deleteAll();
        userRepository.deleteAll();
    }
}