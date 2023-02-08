package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.model.TeacherDTO;
import com.example.facultyfeedback.model.request.TeacherRequest;
import com.example.facultyfeedback.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher")
    public List<TeacherDTO> getTeacher(@RequestParam(value = "department", required = false) String department){
        return teacherService.getTeacher(department);
    }

    @PostMapping("/teacher")
    public TeacherDTO saveTeacher(@RequestBody TeacherRequest teacherRequest){
        return teacherService.save(teacherRequest);
    }
}
