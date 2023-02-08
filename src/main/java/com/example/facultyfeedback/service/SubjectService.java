package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.model.SubjectDTO;
import com.example.facultyfeedback.model.request.SubjectRequest;
import com.example.facultyfeedback.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private SemesterService semesterService;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, SemesterService semesterService) {
        this.subjectRepository = subjectRepository;
        this.semesterService = semesterService;
    }

    public List<SubjectDTO> getSubjects(Long teacherId){
        final List<Subject> subjects;
        if(teacherId != null) {
            subjects = subjectRepository.findAllByTeacherId(teacherId);
        }else{
            subjects = subjectRepository.findAll();
        }
//        List<SubjectDTO> subjectDTOS = new ArrayList<>();
//        for (int i = 0; i < subjects.size(); i++) {
//            final Subject subject = subjects.get(i);
//            subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
//        }

        return subjects.stream()
                .map(subject -> new SubjectDTO(subject.getId(), subject.getName()))
                .collect(Collectors.toList());
    }

    public SubjectDTO save(SubjectRequest subjectRequest){
        final Semester semester = semesterService.findById(subjectRequest.getSemesterId());
        Subject subject = new Subject(null, semester, subjectRequest.getName(),null);
        final Subject savedSubject = subjectRepository.saveAndFlush(subject);
        return new SubjectDTO(savedSubject.getId(),savedSubject.getName());

    }
}
