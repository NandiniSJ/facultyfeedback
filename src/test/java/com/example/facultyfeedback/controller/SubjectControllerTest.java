package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.entity.User;
import com.example.facultyfeedback.model.request.SemesterRequest;
import com.example.facultyfeedback.repositories.SemesterRepository;
import com.example.facultyfeedback.repositories.SubjectRepository;
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
class SubjectControllerTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnListOfAllSubjects() throws Exception {
        Subject subject = new Subject(1L, null, "Maths",null);
        subjectRepository.save(subject);
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);

        this.mockMvc.perform(get("/subject").with(httpBasic("12","n@78880")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(subject.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(subject.getName())));
    }

    @Test
    void shouldSaveSubject() throws Exception {
        Semester semester = new Semester(null, 1, null);
        Semester savedSemester = semesterRepository.save(semester);
        String body = "{\n" +
                "    \"name\": \"Maths\",\n" +
                "    \"semesterId\":"+ savedSemester.getId() + "\n" +
                " \n" +
                "}";

        User user = new User(1L, "12","n@78880");
        userRepository.save(user);

        this.mockMvc.perform(post("/subject").with(httpBasic("12","n@78880"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        final List<Subject> subjects = subjectRepository.findAll();
        assertEquals(1, subjects.size());
        assertEquals("Maths", subjects.get(0).getName());
        assertEquals(1, subjects.get(0).getSemester().getNumber());


    }

    @AfterEach
    void tearDown() {
        subjectRepository.deleteAll();
        userRepository.deleteAll();
    }
}