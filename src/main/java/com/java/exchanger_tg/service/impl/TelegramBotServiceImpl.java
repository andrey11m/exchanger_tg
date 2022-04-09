package com.java.exchanger_tg.service.impl;

import com.java.exchanger_tg.service.BankInfoService;
import com.java.exchanger_tg.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramBotServiceImpl implements TelegramBotService {

    private final TelegramBot bot;
    private final BankInfoService infoService;

    public void serve() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        Message message = update.message();
        long chatId = message.chat().id();
        if (message.text().equals("/mtbank")) {
            bot.execute(new SendMessage(chatId, infoService.getBankInfo().get(MTBankParser.class.getSimpleName())));
        } else {
            bot.execute(new SendMessage(chatId, "Unknown command"));
        }

    }
}
