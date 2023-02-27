package com.example.facultyfeedback.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.model.request.StudentRequest;
import com.example.facultyfeedback.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final SemesterService semesterService;

    @Autowired
    public StudentService(StudentRepository studentRepository, SemesterService semesterService) {
        this.studentRepository = studentRepository;
        this.semesterService = semesterService;
    }

    public List<StudentDTO> getStudents(){
        final List<Student> students = studentRepository.findAll();
//        List<StudentDTO> studentDTOS = new ArrayList<>();
//        for (int i = 0; i < students.size(); i++) {
//            final Student student = students.get(i);
//            studentDTOS.add(new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getRollNum()));
//        }

        return students.stream()
                .map(student -> new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getRollNum(), student.getDepartment()))
                .collect(Collectors.toList());
//        return studentDTOS;
    }

    public StudentDTO save(StudentRequest studentRequest){
        final Semester semester = semesterService.findById(studentRequest.getSemesterId());
        Student student = new Student(null,studentRequest.getFirstName(),studentRequest.getLastName(),studentRequest.getRollNum(),studentRequest.getDepartment(),semester);
        Student savedStudent = studentRepository.saveAndFlush(student);
        return new StudentDTO(savedStudent.getId(), savedStudent.getFirstName(), savedStudent.getLastName(), savedStudent.getRollNum(),savedStudent.getDepartment());
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public StudentDTO findByRollNum(String rollNum){
        Student student = studentRepository.findByRollNum(rollNum);
        return new StudentDTO(student.getId(),student.getFirstName(),student.getLastName(),student.getRollNum(),student.getDepartment());
    }
}
