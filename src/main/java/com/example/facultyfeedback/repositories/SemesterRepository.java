package com.example.facultyfeedback.repositories;

import com.example.facultyfeedback.entity.Semester;
import com.example.facultyfeedback.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
