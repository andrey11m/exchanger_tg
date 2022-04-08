package com.java.exchanger_tg.event;

import com.java.exchanger_tg.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramEvent {

    private final TelegramBotService telegramBotService;

    @EventListener(ApplicationReadyEvent.class)
    public void runTelegramBot() {
        telegramBotService.serve();
    }
}
