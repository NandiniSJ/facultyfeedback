package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/user")
    public ResponseEntity<StudentDTO> getUser(Authentication authentication) {
        StudentDTO student = studentService.findByRollNum(authentication.getName());
        return ResponseEntity.ok(student);
    }

}
