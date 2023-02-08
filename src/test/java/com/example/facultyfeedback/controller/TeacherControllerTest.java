package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.repositories.TeacherRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnListOfTeachers() throws Exception {
        Teacher teacher = new Teacher(1L, "Shilkumar","Jadhav", "Computer Science", null);
        teacherRepository.save(teacher);

        this.mockMvc.perform(get("/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(teacher.getFirstName())));
    }

    @Test
    void shouldSaveTeacher()throws Exception{
        String body = "{\n" +
                "   \"firstName\": \"Shilkumar\",\n" +
                "   \"lastName\": \"Jadhav\" , \n" +
                "   \"department\": \"Computer Science\"  \n" +
                "}";

        this.mockMvc.perform(post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                        .andExpect(status().isOk());

        List<Teacher> teachers = teacherRepository.findAll();
        assertEquals(1, teachers.size());
        assertEquals("Computer Science", teachers.get(0).getDepartment());
    }

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

}