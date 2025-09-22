package com.example.kangawa_stamp_rally.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class GetStampDto{
    private String uuid;
    private String stampNo;
    private LocalDateTime datetime;
}
