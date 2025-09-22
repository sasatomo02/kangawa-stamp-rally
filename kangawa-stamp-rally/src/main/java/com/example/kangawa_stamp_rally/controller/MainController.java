package com.example.kangawa_stamp_rally.controller;

import com.example.kangawa_stamp_rally.dto.GetStampDto;
import com.example.kangawa_stamp_rally.dto.QuizDto;
import com.example.kangawa_stamp_rally.dto.ReturnStampInfoDto;
import com.example.kangawa_stamp_rally.repository.QuizRepository;
import com.example.kangawa_stamp_rally.service.GetStampService;
import com.example.kangawa_stamp_rally.service.LogService;
import com.example.kangawa_stamp_rally.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class MainController {

    private final LogService logService;
    private final GetStampService getStampService;
    private final UserService userService;

    //接続テスト用
    @GetMapping("/test")
    public String testConnect() {
        logService.connectLog("testConnect");
        return "Hello";
    }

    /**
     * 初回登録をするController
     * @param uuid　フロントで発行されるUUID
     * @param username ユーザの決める任意な名前
     * &#064;return　成功:Responce.OK  失敗:400 登録済みのIDです
     */
    @PostMapping("/user")
    public ResponseEntity<String> userAdd(@RequestParam("uuid") String uuid,
                                          String username){
        logService.connectLog("user");
        try{
            userService.userAdd(uuid,username);
            return ResponseEntity.ok("ユーザーが正常に追加されました");

        }catch (Exception e){
            throw new RuntimeException("登録済みのIDです");
        }
    }

    //TOP画面
    @GetMapping("/top")
    public List<ReturnStampInfoDto> top(@RequestParam("uuid") String uuid) {
        logService.connectLog("top");
        return getStampService.getAcquiredStampsWithUrls(uuid);
    }

    //スタンプ登録
    @PostMapping("/add")
    public ReturnStampInfoDto add(@RequestParam("uuid") String uuid,
                                  @RequestParam("stampId") String stampId) {
        logService.connectLog("add");
        return getStampService.addStampToDb(uuid, stampId);
    }

    //クイズ取得
    @GetMapping("/quiz")
    public QuizDto quiz(@RequestParam("stampId") String stampId) {
        logService.connectLog("quiz");
        return getStampService.getQuiz(stampId);
    }
}
