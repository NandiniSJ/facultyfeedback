package com.example.facultyfeedback.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

    private Long teacherId;
    private Long studentId;
    private int questionNum;
    private int points;
}
