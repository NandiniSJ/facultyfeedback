package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.model.SubjectDTO;
import com.example.facultyfeedback.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDTO> getSubjects(){
        final List<Subject> subjects = subjectRepository.findAll();

//        List<SubjectDTO> subjectDTOS = new ArrayList<>();
//        for (int i = 0; i < subjects.size(); i++) {
//            final Subject subject = subjects.get(i);
//            subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
//        }

        return subjects.stream()
                .map(subject -> new SubjectDTO(subject.getId(), subject.getName()))
                .collect(Collectors.toList());
    }
}
