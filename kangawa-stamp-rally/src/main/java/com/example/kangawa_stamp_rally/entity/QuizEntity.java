package com.example.kangawa_stamp_rally.entity;

import jakarta.persistence.*; // Spring Boot 3+ (Jakarta EE 9+) を想定。Spring Boot 2.x なら javax.persistence.*
import lombok.Data;

@Entity
@Data
@Table(name = "QUIZ")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "QUIZ_NO")
    private Integer quizNo;

    @Column(name = "QUIZ_TEXT", nullable = false, length = 100)
    private String quizText;

    @Column(name = "OPTION1", nullable = false, length = 50)
    private String option1;

    @Column(name = "OPTION2", nullable = false, length = 50)
    private String option2;

    @Column(name = "OPTION3", nullable = false, length = 50)
    private String option3;

    @Column(name = "OPTION4", nullable = false, length = 50)
    private String option4;

    @Column(name = "ANSWER_NO", nullable = false)
    private Integer answerNo;

    @Column(name = "EXPLANATION", nullable = false, length = 100)
    private String explanation;


}