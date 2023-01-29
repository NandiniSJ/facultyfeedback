package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.model.SemesterDTO;
import com.example.facultyfeedback.repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
