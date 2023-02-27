package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Student;
import com.example.facultyfeedback.model.StudentDTO;
import com.example.facultyfeedback.model.request.StudentRequest;
import com.example.facultyfeedback.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest {

    private StudentService studentService;

    private SemesterService semesterService = Mockito.mock(SemesterService.class);

    private final StudentRepository studentRepository = Mockito.mock(StudentRepository.class);


    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository, semesterService);
    }

    @Test
    void shouldReturnAllStudents() {
        Student student = new Student(1L, "Nandini", "Jadhav", "12","civil", null);
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));

        final List<StudentDTO> students = studentService.getStudents();

        StudentDTO expected = new StudentDTO(1L, "Nandini", "Jadhav", "12","civil");
        assertEquals(List.of(expected), students);
    }

    @Test
    void shouldSaveStudent() {
        Semester semester = new Semester(1L, 1, null);
        StudentRequest studentRequest = new StudentRequest("Nandini", "Jadhav", "12", "civil",1L);
        Mockito.when(semesterService.findById(1L)).thenReturn(semester);
        Student student = new Student(1L,"Nandini", "Jadhav", "12", "civil",semester);
        Mockito.when(studentRepository.saveAndFlush(Mockito.any())).thenReturn(student);

        StudentDTO studentDTO = studentService.save(studentRequest);

        StudentDTO expected = new StudentDTO(1L, "Nandini", "Jadhav", "12","civil");
        assertEquals(expected, studentDTO);

    }

    @Test
    void shouldReturnStudentByRollNum(){
        Student student = new Student(1L,"Nandini","Jadhav","12", "Civil",null);
        Mockito.when(studentRepository.findByRollNum(student.getRollNum())).thenReturn(student);

        StudentDTO studentDTO = studentService.findByRollNum(student.getRollNum());

        assertEquals(studentDTO.getFirstName(), "Nandini");
        assertEquals(studentDTO.getLastName(), "Jadhav");
        assertEquals(studentDTO.getDepartment(), "Civil");
        assertEquals(studentDTO.getRollNum(), "12");
    }
}