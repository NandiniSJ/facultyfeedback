package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.model.SubjectDTO;
import com.example.facultyfeedback.model.request.SubjectRequest;
import com.example.facultyfeedback.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SubjectController {

  @Autowired
   private SubjectService subjectService;

  @GetMapping("/subject")
  public List<SubjectDTO> getSubjects() {
      return subjectService.getSubjects();
  }

  @PostMapping("/subject")
    public SubjectDTO saveSubject(@RequestBody SubjectRequest subjectRequest){
      return subjectService.save(subjectRequest);
  }
}
