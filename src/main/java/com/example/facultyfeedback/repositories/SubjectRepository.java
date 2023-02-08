package com.example.facultyfeedback.repositories;

import com.example.facultyfeedback.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByTeacherId(Long teacherId);
}
