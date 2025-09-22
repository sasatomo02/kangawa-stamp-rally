package com.example.kangawa_stamp_rally.service;

import com.example.kangawa_stamp_rally.entity.UserEntity;
import com.example.kangawa_stamp_rally.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public void userAdd(String uuid,String username){
        if(userRepository.findById(uuid).isPresent()){
            throw new RuntimeException("登録済みのIDです");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(uuid);
        userEntity.setUserName(username);
        userRepository.save(userEntity);
    }
}
