package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.entity.User;
import com.example.facultyfeedback.repositories.TeacherRepository;
import com.example.facultyfeedback.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
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
    private UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnListOfTeachers() throws Exception {
        Teacher teacher = new Teacher(1L, "Shilkumar","Jadhav", "Computer Science", null);
        teacherRepository.save(teacher);
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);

        this.mockMvc.perform(get("/teacher").with(httpBasic("12","n@78880")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(teacher.getFirstName())));
    }

    @Test
    void shouldSaveTeacher()throws Exception{
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);
        String body = "{\n" +
                "   \"firstName\": \"Shilkumar\",\n" +
                "   \"lastName\": \"Jadhav\" , \n" +
                "   \"department\": \"Computer Science\"  \n" +
                "}";

        this.mockMvc.perform(post("/teacher").with(httpBasic("12","n@78880"))
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
        userRepository.deleteAll();
    }

}