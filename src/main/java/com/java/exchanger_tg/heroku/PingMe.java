package com.java.exchanger_tg.heroku;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PingMe {

    @Scheduled(fixedRateString = "PT20M")
    @SneakyThrows
    public void ping() {
        Document document = Jsoup.connect("https://www.google.com/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36")
                .get();
        document.select("#SIvCob");
    }
}
