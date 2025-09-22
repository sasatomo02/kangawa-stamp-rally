package com.example.kangawa_stamp_rally.dto;

import lombok.Data;

@Data
public class QuizDto {
    private Integer quizNo;
    private String quizText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer answerNo;
    private String explanation;
}