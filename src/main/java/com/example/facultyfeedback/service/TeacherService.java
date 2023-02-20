package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.model.TeacherDTO;
import com.example.facultyfeedback.model.request.TeacherRequest;
import com.example.facultyfeedback.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDTO> getTeacher(String department){
        List<Teacher> teachers;
        if(department != null) {
            teachers = teacherRepository.findAllByDepartment(department);
        } else {
            teachers = teacherRepository.findAll();
        }

        return teachers.stream()
                .map(teacher -> new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastname(), teacher.getDepartment()))
                .collect(Collectors.toList());

    }
    public TeacherDTO save(TeacherRequest teacherRequest){
        Teacher teacher = new Teacher(null,teacherRequest.getFirstName(),teacherRequest.getLastName(),teacherRequest.getDepartment(),null);
        Teacher savedTeacher = teacherRepository.saveAndFlush(teacher);
        return new TeacherDTO(savedTeacher.getId(),savedTeacher.getFirstName(), savedTeacher.getLastname(),savedTeacher.getDepartment());
    }

    public Teacher findById(Long id) {
        return teacherRepository.findById(id).get();
    }
}
