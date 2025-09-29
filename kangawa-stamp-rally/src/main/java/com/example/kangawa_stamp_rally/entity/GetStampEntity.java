package com.example.kangawa_stamp_rally.entity;

import jakarta.persistence.*; // Jakarta EE 9+ を想定
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "GET_STAMP")
public class GetStampEntity {

    @EmbeddedId // 複合主キーを示す
    private GetStampId id; // 上記で定義した複合主キーのクラス

    // UserEntityとの関連
    @MapsId("uuid") // GetStampIdクラスの"uuid"フィールドにマッピングする
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UUID", referencedColumnName = "UUID", nullable = false)
    private UserEntity user;

    // StampEntityとの関連
    @MapsId("stampNo") // GetStampIdクラスの"stampNo"フィールドにマッピングする
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAMP_NO", referencedColumnName = "STAMP_NO", nullable = false)
    private StampEntity stamp;

    @Column(name = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime datetime;

}