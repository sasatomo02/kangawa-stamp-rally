package com.example.kangawa_stamp_rally.repository;

import com.example.kangawa_stamp_rally.dto.GetStampDto;
import com.example.kangawa_stamp_rally.entity.GetStampEntity;
import com.example.kangawa_stamp_rally.entity.GetStampId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetStampRepository extends JpaRepository<GetStampEntity, GetStampId> {
    List<GetStampEntity> findById_Uuid(String uuid);
}
