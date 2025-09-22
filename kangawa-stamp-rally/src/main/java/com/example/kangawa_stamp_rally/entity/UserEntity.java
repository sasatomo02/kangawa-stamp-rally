package com.example.kangawa_stamp_rally.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @Column(name = "UUID", length = 100)
    private String uuid;

    @Column(name = "USER_NAME", length = 50)
    private String userName;

}