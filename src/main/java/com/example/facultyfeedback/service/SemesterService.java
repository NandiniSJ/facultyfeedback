package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.model.SemesterDTO;
import com.example.facultyfeedback.model.request.SemesterRequest;
import com.example.facultyfeedback.repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemesterService {

   private SemesterRepository semesterRepository;

   @Autowired
   public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<SemesterDTO> getSemester(){
       List<Semester> semesters = semesterRepository.findAll();

//       List<SemesterDTO> semesterDTOS = new ArrayList<>();

//        for (int i = 0; i < semesters.size(); i++) {
//            Semester semester = semesters.get(i);
//            semesterDTOS.add(new SemesterDTO(semester.getId(), semester.getNumber()));
//        }

        return semesters.stream()
                .map(semester ->new SemesterDTO(semester.getId(), semester.getNumber()))
                .collect(Collectors.toList());
   }

    public SemesterDTO save(SemesterRequest semesterRequest) {
        Semester semester = new Semester(null, semesterRequest.getNumber(), null);
        final Semester savedSemester = semesterRepository.saveAndFlush(semester);
        return new SemesterDTO(savedSemester.getId(), savedSemester.getNumber());
    }

    public Semester findById(Long id){
        Optional<Semester> optionalSemester = semesterRepository.findById(id);
        if(optionalSemester.isPresent()){
            return optionalSemester.get();
        }
        throw new RuntimeException("Semester with id " + id + " not found");
    }
}
