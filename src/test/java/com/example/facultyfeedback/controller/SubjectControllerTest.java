package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.model.request.SemesterRequest;
import com.example.facultyfeedback.repositories.SemesterRepository;
import com.example.facultyfeedback.repositories.SubjectRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SubjectControllerTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SemesterRepository semesterRepository;

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

    @Test
    void shouldSaveSubject() throws Exception {
        String body = "{\n" +
                "    \"name\": \"Maths\",\n" +
                "    \"semesterId\":1\n" +
                " \n" +
                "}";

        Semester semester = new Semester(null, 1, null);
        semesterRepository.save(semester);

        this.mockMvc.perform(post("/subject")
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
    }
}