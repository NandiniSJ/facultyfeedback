package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Feedback;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.model.FeedbackDTO;
import com.example.facultyfeedback.model.request.FeedbackRequest;
import com.example.facultyfeedback.repositories.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


class FeedbackServiceTest {

    FeedbackRepository feedbackRepository = Mockito.mock(FeedbackRepository.class);

    TeacherService teacherService = Mockito.mock(TeacherService.class);
    StudentService studentService = Mockito.mock(StudentService.class);
    FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        feedbackService = new FeedbackService(teacherService, studentService, feedbackRepository);
    }

    @Test
    void shouldSaveFeedback(){
        Teacher teacher = new Teacher(1L,"XYZ", "ABC", "AAA",null);
        Mockito.when(teacherService.findById(1L)).thenReturn(teacher);
        Student student = new Student(1L, "NNN", "JJJ", "12","AAA",null);
        Mockito.when(studentService.findById(1L)).thenReturn(student);
        Feedback feedback = new Feedback(1L,teacher,student, 10, 15);
        Mockito.when(feedbackRepository.saveAndFlush(Mockito.any())).thenReturn(feedback);

        FeedbackRequest feedbackRequest = new FeedbackRequest(1L, 1L, 10, 15);
        FeedbackDTO savedFeedBack = feedbackService.save(feedbackRequest);

        FeedbackDTO expected = new FeedbackDTO(1L, 1L, 1L, 10, 15);
        assertEquals(expected, savedFeedBack);
    }
}