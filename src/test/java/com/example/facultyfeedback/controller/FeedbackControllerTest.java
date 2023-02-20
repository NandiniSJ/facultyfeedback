package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Feedback;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.repositories.FeedbackRepository;
import com.example.facultyfeedback.repositories.StudentRepository;
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
class FeedbackControllerTest {

   @Autowired
   private FeedbackRepository feedbackRepository;

   @Autowired
   private MockMvc mockMvc;

   @Autowired
    private TeacherRepository teacherRepository;
   @Autowired
   private StudentRepository studentRepository;

   @Test
    void shouldSaveFeedback() throws Exception{
       Teacher teacher = new Teacher(1L, "aaa","bbb","ccc",null);
       Teacher savedTeacher = teacherRepository.save(teacher);
       Student student = new Student(1L,"sss", "jjj", "12", "cs", null);
       Student savedStudent = studentRepository.save(student);


       String body = "{\n" +
               " \"teacherId\": \""+savedTeacher.getId()+"\",\n" +
               " \"studentId\" : \""+savedStudent.getId()+"\",\n" +
               " \"questionNum\" : \"10\",\n" +
               " \"points\" : \"12\"\n" +
               "}";

       this.mockMvc.perform(post("/feedback")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(body))
               .andExpect(status().isOk());

       List<Feedback> savedFeedback = feedbackRepository.findAll();

       assertEquals(1, savedFeedback.size());
       assertEquals(12, savedFeedback.get(0).getPoints());
   }


}