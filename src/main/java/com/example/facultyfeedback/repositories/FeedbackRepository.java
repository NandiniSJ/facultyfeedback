package com.example.facultyfeedback.repositories;

import com.example.facultyfeedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query(value = "select avg(points) from feedback where teacher_id = :teacher_id", nativeQuery = true)
    public double getAvg(@Param("teacher_id") Long teacherId);
}
