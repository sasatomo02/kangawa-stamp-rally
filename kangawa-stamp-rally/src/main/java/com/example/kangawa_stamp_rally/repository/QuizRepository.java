package com.example.kangawa_stamp_rally.repository;

import com.example.kangawa_stamp_rally.entity.QuizEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Integer> {
}
