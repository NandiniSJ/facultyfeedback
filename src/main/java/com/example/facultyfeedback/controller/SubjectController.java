package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.model.SubjectDTO;
import com.example.facultyfeedback.model.request.SubjectRequest;
import com.example.facultyfeedback.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

  @Autowired
   private SubjectService subjectService;

  @GetMapping("/subject")
  public List<SubjectDTO> getSubjects(@RequestParam(value = "teacherId", required = false) Long teacherId) {
      return subjectService.getSubjects(teacherId);
  }

  @PostMapping("/subject")
    public SubjectDTO saveSubject(@RequestBody SubjectRequest subjectRequest){
      return subjectService.save(subjectRequest);
  }
}
