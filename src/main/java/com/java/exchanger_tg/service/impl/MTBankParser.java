package com.java.exchanger_tg.service.impl;

import com.java.exchanger_tg.service.HTMLParser;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MTBankParser implements HTMLParser {

    @SneakyThrows
    public String parse() {
        Document document = Jsoup.connect("https://www.mtbank.by/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36")
                .get();
        Elements select = document.select("#comp_b0d1ae846a9ad6171450c4cf6a484d11 > div > div > div > div > div.home-exchange__tab.js-tab-content--converter.visible > ul");
        List<String> infoList = new ArrayList<>();
        for (Element element : select.select("li")) {
            infoList.add(element.text());
        }
        return createResponseMessage(infoList);
    }

    private String createResponseMessage(List<String> infoList) {
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
