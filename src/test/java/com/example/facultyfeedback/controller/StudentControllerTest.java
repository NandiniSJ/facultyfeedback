package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.entity.User;
import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.repositories.SemesterRepository;
import com.example.facultyfeedback.repositories.StudentRepository;
import com.example.facultyfeedback.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnListOfStudents() throws Exception {
        Student student = new Student(1L, "Nandini", "Jadhav", "12","civil", null);
        studentRepository.save(student);
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);

        this.mockMvc.perform(get("/student").with(httpBasic("12","n@78880")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(student.getLastName())))
                .andExpect(jsonPath("$[0].rollNum", is(student.getRollNum())))
                .andExpect(jsonPath("$[0].department", is(student.getDepartment())));

    }


    @Test
    void shouldSaveStudent()throws Exception {
        Semester semester = new Semester(null, 1, null);
        final Semester savedSemester = semesterRepository.save(semester);
        User user = new User(1L, "12","n@78880");
        userRepository.save(user);

        String body = "{\n" +
                "   \"firstName\": \"Nandini\",\n" +
                "   \"lastName\": \"Jadhav\",\n" +
                "   \"rollNum\": \"12\",\n" +
                "   \"department\": \"civil\",\n" +
                "   \"semesterId\": "+savedSemester.getId() +"\n" +
                "}";
        this.mockMvc.perform(post("/student").with(httpBasic("12","n@78880"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        final List<Student> students = studentRepository.findAll();

        assertEquals(1, students.size());
        assertEquals("Nandini", students.get(0).getFirstName());
        assertEquals("Jadhav", students.get(0).getLastName());
        assertEquals("12", students.get(0).getRollNum());
        assertEquals("civil", students.get(0).getDepartment());

    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
        userRepository.deleteAll();
    }
}