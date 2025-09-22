package com.example.kangawa_stamp_rally.repository;

import com.example.kangawa_stamp_rally.entity.GetStampEntity;
import com.example.kangawa_stamp_rally.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
}
