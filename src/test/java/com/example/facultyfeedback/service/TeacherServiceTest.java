package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Teacher;
import com.example.facultyfeedback.model.TeacherDTO;
import com.example.facultyfeedback.model.request.TeacherRequest;
import com.example.facultyfeedback.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private TeacherRepository teacherRepository = Mockito.mock(TeacherRepository.class);

    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        teacherService = new TeacherService(teacherRepository);
    }

    @Test
    void shouldSaveTeacher(){
        Teacher teacher = new Teacher(1L, "Shilkumar", "Jadhav", null);
        Mockito.when(teacherRepository.saveAndFlush(Mockito.any())).thenReturn(teacher);
        TeacherRequest teacherRequest = new TeacherRequest("Shilkumar", "Jadhav");

        final TeacherDTO savedTeacher = teacherService.save(teacherRequest);

        TeacherDTO expected = new TeacherDTO(1L,"Shilkumar", "Jadhav");
        assertEquals(expected, savedTeacher);
    }
}