package com.example.facultyfeedback.controller;

import java.util.List;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
   private StudentService studentService;

    @GetMapping("/student")
    public List<StudentDTO> getStudents(){
        return studentService.getStudents();
    }

}
