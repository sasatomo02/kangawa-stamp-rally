package com.example.kangawa_stamp_rally.controller;

import com.example.kangawa_stamp_rally.dto.UserCountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

@RestController
public class SseController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/events")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        this.emitters.add(emitter);

        // クライアントが切断された場合の処理
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        return emitter;
    }

    public void sendDataUpdate(UserCountDto data) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data(data));
            } catch (IOException e) {
                // 送信に失敗した場合はリストから削除
                this.emitters.remove(emitter);
            }
        }
    }
}