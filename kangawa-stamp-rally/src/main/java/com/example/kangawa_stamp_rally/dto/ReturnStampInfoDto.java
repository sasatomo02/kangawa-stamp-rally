package com.example.kangawa_stamp_rally.dto;

import lombok.Data;
@Data
public class ReturnStampInfoDto {
    private String stampNo;
    private String stampName;
    private String stampText;
    private String imgPath;
    private QuizDto quizDto;
    private String date;
}
