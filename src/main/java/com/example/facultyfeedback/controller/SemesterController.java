package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.model.SemesterDTO;
import com.example.facultyfeedback.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/semester")
    public List<SemesterDTO> getSemester(){
        return semesterService.getSemester();
    }

}
