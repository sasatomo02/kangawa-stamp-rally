package com.example.kangawa_stamp_rally.controller;


import com.example.kangawa_stamp_rally.dto.UserCountDto;
import com.example.kangawa_stamp_rally.service.UserCountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CompletionRateController {

    private UserCountService userCountService;

    @GetMapping("/complete")
    public String view(Model model){

        UserCountDto data = userCountService.count();

        model.addAttribute("userCount", data.getUserCount());
        model.addAttribute("completeCount", data.getCompleteCount());

        return "completion_rate";
    }
}
