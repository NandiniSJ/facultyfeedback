package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Subject;
import com.example.facultyfeedback.model.SubjectDTO;
import com.example.facultyfeedback.model.request.SubjectRequest;
import com.example.facultyfeedback.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubjectServiceTest {

    private SubjectRepository subjectRepository = Mockito.mock(SubjectRepository.class);

    private SemesterService semesterService = Mockito.mock(SemesterService.class);
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        subjectService = new SubjectService(subjectRepository, semesterService);
    }

    @Test
    void shouldReturnListOfAllSubjects() {
        Subject subject = new Subject(1L, null, "Maths");
        Mockito.when(subjectRepository.findAll()).thenReturn(List.of(subject));

        final List<SubjectDTO> subjects = subjectService.getSubjects();

        SubjectDTO expected = new SubjectDTO(1L, "Maths");
        assertEquals(List.of(expected),subjects);
    }

    @Test
    void shouldSaveSubject() {
        SubjectRequest subjectRequest = new SubjectRequest("Maths", 1L);
        Semester semester = new Semester(1L, 1, null);
        Mockito.when(semesterService.findById(1L)).thenReturn(semester);
        Subject subject = new Subject(1L, semester, "Maths");
        Mockito.when(subjectRepository.saveAndFlush(Mockito.any())).thenReturn(subject);

        final SubjectDTO subjectDTO = subjectService.save(subjectRequest);

        SubjectDTO expected = new SubjectDTO(1L, "Maths");
        assertEquals(expected, subjectDTO);
    }
}