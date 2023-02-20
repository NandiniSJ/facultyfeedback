package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Feedback;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.model.FeedbackDTO;
import com.example.facultyfeedback.model.request.FeedbackRequest;
import com.example.facultyfeedback.repositories.FeedbackRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private TeacherService teacherService;
    private StudentService studentService;
    private FeedbackRepository feedbackRepository;
    @Autowired
    public FeedbackService(TeacherService teacherService, StudentService studentService, FeedbackRepository feedbackRepository) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackDTO save(FeedbackRequest feedbackRequest){
        Teacher teacher = teacherService.findById(feedbackRequest.getTeacherId());
        Student student = studentService.findById(feedbackRequest.getStudentId());
        Feedback feedback = new Feedback(null,teacher,student, feedbackRequest.getQuestionNum(),feedbackRequest.getPoints());
        Feedback savedFeedBack = feedbackRepository.saveAndFlush(feedback);
        return new FeedbackDTO(savedFeedBack.getId(),savedFeedBack.getTeacher().getId(),savedFeedBack.getStudent().getId(),savedFeedBack.getQuestionNum(),savedFeedBack.getPoints());
    }

    public double findAvgOfPoints(Long teacherId){
       return feedbackRepository.getAvg(teacherId);
    }

}
