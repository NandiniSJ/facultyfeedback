package com.example.facultyfeedback.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequest {

    private String name;
    private Long semesterId;
}
