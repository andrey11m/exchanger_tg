package com.java.exchanger_tg.service.impl;

import com.java.exchanger_tg.service.HTMLParser;
import com.java.exchanger_tg.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramTelegramBotServiceImpl implements TelegramBotService {

    private final HTMLParser htmlParser;
    private final TelegramBot bot;

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
            List<String> infoList = htmlParser.parse();
            String responseMessage = createMessage(infoList);
            bot.execute(new SendMessage(chatId, responseMessage));
        } else {
            bot.execute(new SendMessage(chatId, "Unknown command"));
        }

    }

    private String createMessage(List<String> infoList) {
        StringBuilder response = new StringBuilder();
        infoList.stream().map(StringBuilder::new)
                .forEach(line -> {
                    int index = line.indexOf("покупка");
                    line.insert(index, ":\n");
                    response.append(line).append("\n");
                });
        return String.valueOf(response);
    }
}
