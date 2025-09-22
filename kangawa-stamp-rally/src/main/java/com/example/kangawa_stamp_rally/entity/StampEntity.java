package com.example.kangawa_stamp_rally.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "STAMP")
public class StampEntity {

    @Id
    @Column(name = "STAMP_NO", length = 20)
    private String stampNo;

    @Column(name = "STAMP_NAME", length = 50)
    private String stampName;

    @Column(name = "STAMP_TEXT", columnDefinition = "TEXT") // TEXT型はcolumnDefinitionで指定することが多い
    private String stampText;

    @Column(name = "IMG_PATH", length = 255)
    private String imgPath;

    @JoinColumn(name = "QUIZ_NO", nullable = false) // QUIZ_NOカラムが外部キー
    private Integer quizNo; // Quizオブジェクトを直接参照

}