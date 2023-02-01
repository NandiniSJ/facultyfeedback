package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.repositories.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldSaveTeacher()throws Exception{
        String body = "{\n" +
                "   \"firstName\": \"Shilkumar\",\n" +
                "   \"lastName\": \"Jadhav\"  \n" +
                "}";

        this.mockMvc.perform(post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        List<Teacher> teachers = teacherRepository.findAll();
        assertEquals(1, teachers.size());
        assertEquals("Shilkumar", teachers.get(0).getFirstName());
    }

}