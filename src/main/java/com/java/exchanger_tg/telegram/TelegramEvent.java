package com.java.exchanger_tg.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramEvent {

    private final Bot bot;

    @EventListener(ApplicationReadyEvent.class)
    public void runTelegramBot() {
        bot.serve();
    }
}
