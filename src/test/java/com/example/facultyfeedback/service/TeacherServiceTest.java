package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.model.TeacherDTO;
import com.example.facultyfeedback.model.request.TeacherRequest;
import com.example.facultyfeedback.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private TeacherRepository teacherRepository = Mockito.mock(TeacherRepository.class);

    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        teacherService = new TeacherService(teacherRepository);
    }

    @Test
    void shouldReturnAllTeachers() {
        Teacher teacher = new Teacher(1L, "Shilkumar","Jadhav", "Computer Science", null);
        Mockito.when(teacherRepository.findAll()).thenReturn(List.of(teacher));

        final List<TeacherDTO> teachers = teacherService.getTeacher(null);

        TeacherDTO expected = new TeacherDTO(1L, "Shilkumar", "Jadhav","Computer Science");
        assertEquals(List.of(expected), teachers);
    }

    @Test
    void shouldReturnAllTeachesByDepartment(){
        Teacher teacher = new Teacher(1L,"Shilkumar", "Jadhav", "CS", null);
        Mockito.when(teacherRepository.findAllByDepartment("CS")).thenReturn(List.of(teacher));

        List<TeacherDTO> teachers = teacherService.getTeacher("CS");

        assertEquals(1, teachers.size());
        TeacherDTO expected = new TeacherDTO(1L,"Shilkumar", "Jadhav", "CS");
        assertEquals(expected, teachers.get(0));
    }


    @Test
    void shouldSaveTeacher(){
        Teacher teacher = new Teacher(1L, "Shilkumar", "Jadhav", "Computer Science",null);
        Mockito.when(teacherRepository.saveAndFlush(Mockito.any())).thenReturn(teacher);
        TeacherRequest teacherRequest = new TeacherRequest("Shilkumar", "Jadhav","Computer Science");

        final TeacherDTO savedTeacher = teacherService.save(teacherRequest);

        TeacherDTO expected = new TeacherDTO(1L,"Shilkumar", "Jadhav","Computer Science");
        assertEquals(expected, savedTeacher);
    }
}