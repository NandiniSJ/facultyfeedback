package com.example.facultyfeedback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private Long id;
    private Long teacherId;
    private Long studentId;
    private int questionNum;
    private int points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        return questionNum == that.questionNum && points == that.points && Objects.equals(id, that.id) && Objects.equals(teacherId, that.teacherId) && Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherId, studentId, questionNum, points);
    }
}
