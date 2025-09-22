package com.example.kangawa_stamp_rally.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogService {

    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public void connectLog(String name){
        System.out.println("----------------------------------------------------------");
        System.out.println(formatter1.format(LocalDateTime.now()) + "  接続完了:" + name );
        System.out.println("----------------------------------------------------------");
    }
}
