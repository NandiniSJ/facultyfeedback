package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.model.SubjectDTO;
import com.example.facultyfeedback.repositories.SemesterRepository;
import com.example.facultyfeedback.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubjectServiceTest {

    private SubjectRepository subjectRepository = Mockito.mock(SubjectRepository.class);

    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        subjectService = new SubjectService(subjectRepository);
    }

    @Test
    void shouldReturnListOfAllSubjects() {
        Subject subject = new Subject(1L, null, "Maths");
        Mockito.when(subjectRepository.findAll()).thenReturn(List.of(subject));

        final List<SubjectDTO> subjects = subjectService.getSubjects();

        SubjectDTO expected = new SubjectDTO(1L, "Maths");
        assertEquals(List.of(expected),subjects);
    }
}