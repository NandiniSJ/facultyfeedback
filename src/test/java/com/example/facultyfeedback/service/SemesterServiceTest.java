package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.model.SemesterDTO;
import com.example.facultyfeedback.model.request.SemesterRequest;
import com.example.facultyfeedback.model.request.SubjectRequest;
import com.example.facultyfeedback.repositories.SemesterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SemesterServiceTest {

    private SemesterRepository semesterRepository = Mockito.mock(SemesterRepository.class);

    private SemesterService semesterService;
    @BeforeEach
    void setUp() {
        semesterService = new SemesterService(semesterRepository);
    }

    @Test
    void shouldReturnAllSemesters() {
        Semester semester = new Semester(1L, 1, null);
        Mockito.when(semesterRepository.findAll()).thenReturn(List.of(semester));

        final List<SemesterDTO> semesters = semesterService.getSemester();

        SemesterDTO expected = new SemesterDTO(1L, 1);
        assertEquals(List.of(expected), semesters);
    }

    @Test
    void shouldSaveSemester() {
        SemesterRequest semesterRequest = new SemesterRequest(1);
        Semester semester = new Semester(1L,1,null);
        Mockito.when(semesterRepository.saveAndFlush(Mockito.any())).thenReturn(semester);

        final SemesterDTO semesterDTO = semesterService.save(semesterRequest);

        SemesterDTO expected = new SemesterDTO(1L, 1);
        assertEquals(expected, semesterDTO);
    }
}