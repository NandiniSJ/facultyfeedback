package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.model.TeacherDTO;
import com.example.facultyfeedback.model.request.TeacherRequest;
import com.example.facultyfeedback.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teacher")
    public TeacherDTO saveTeacher(@RequestBody TeacherRequest teacherRequest){
        return teacherService.save(teacherRequest);
    }
}
