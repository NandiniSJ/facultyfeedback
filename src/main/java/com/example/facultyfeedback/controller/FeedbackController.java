package com.example.facultyfeedback.controller;

import com.example.facultyfeedback.model.FeedbackDTO;
import com.example.facultyfeedback.model.request.FeedbackRequest;
import com.example.facultyfeedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/feedback")
    public FeedbackDTO saveFeedback(@RequestBody FeedbackRequest feedbackRequest){
        return feedbackService.save(feedbackRequest);
    }

    @GetMapping("/feedback/points")
    public double getAvgOfPoints(@RequestParam(value = "teacherId") Long teacherId ){
        return feedbackService.findAvgOfPoints(teacherId);
    }
}
