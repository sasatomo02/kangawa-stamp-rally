package com.example.kangawa_stamp_rally.service;

import com.example.kangawa_stamp_rally.controller.SseController;
import com.example.kangawa_stamp_rally.dto.UserCountDto;
import com.example.kangawa_stamp_rally.entity.UserEntity;
import com.example.kangawa_stamp_rally.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserCountService  userCountService;
    private SseController sseController;

    public void userAdd(String uuid,String username){
        if(userRepository.findById(uuid).isEmpty()) {
            try {
                UserEntity userEntity = new UserEntity();
                userEntity.setUuid(uuid);
                userEntity.setUserName(username);
                userRepository.save(userEntity);
                //SSE検知
                UserCountDto countDto = userCountService.count();
                sseController.sendDataUpdate(countDto);
            } catch (Exception e) {
                throw new RuntimeException("システムエラーが発生しました");
            }
        }
    }
}
