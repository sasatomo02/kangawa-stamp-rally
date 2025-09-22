package com.example.kangawa_stamp_rally.repository;

import com.example.kangawa_stamp_rally.entity.GetStampEntity;
import com.example.kangawa_stamp_rally.entity.GetStampId;
import com.example.kangawa_stamp_rally.entity.StampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepository extends JpaRepository<StampEntity, String> {
}
