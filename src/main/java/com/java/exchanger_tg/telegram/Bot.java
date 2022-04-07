package com.java.exchanger_tg.telegram;

import com.java.exchanger_tg.parser.HTMLParser;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Bot {


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
        List<String> list = htmlParser.parse();
        long chatId = message.chat().id();
        list.forEach(s -> bot.execute(new SendMessage(chatId, s)));
    }
}
