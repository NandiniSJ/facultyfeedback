package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.model.TeacherDTO;
import com.example.facultyfeedback.model.request.TeacherRequest;
import com.example.facultyfeedback.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDTO save(TeacherRequest teacherRequest){
        Teacher teacher = new Teacher(1L,"Shilkumar", "Jadhav", null);
        Teacher savedTeacher = teacherRepository.saveAndFlush(teacher);
        return new TeacherDTO(savedTeacher.getId(),savedTeacher.getFirstName(), savedTeacher.getLastname());
    }
}
