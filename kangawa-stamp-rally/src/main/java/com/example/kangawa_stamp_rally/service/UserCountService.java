package com.example.kangawa_stamp_rally.service;

import com.example.kangawa_stamp_rally.dto.UserCountDto;
import com.example.kangawa_stamp_rally.repository.GetStampRepository;
import com.example.kangawa_stamp_rally.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCountService {
    UserRepository userRepository;
    GetStampRepository getStampRepository;

    public UserCountDto count(){
        UserCountDto userCountDto = new UserCountDto();
        userCountDto.setUserCount(userRepository.count());
        userCountDto.setCompleteCount(getStampRepository.countUsersWithFourStamps());
        return userCountDto;
    }
}
