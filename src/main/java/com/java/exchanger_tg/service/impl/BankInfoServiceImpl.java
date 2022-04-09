package com.java.exchanger_tg.service.impl;

import com.java.exchanger_tg.service.BankInfoService;
import com.java.exchanger_tg.service.HTMLParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class BankInfoServiceImpl implements BankInfoService {

    @Autowired
    private List<HTMLParser> parsers;

    private HashMap<String, String> info = new HashMap<>();

    @Scheduled(fixedRateString = "PT5M")
    private void updateBankInfo() {
        parsers.forEach(parser -> info.put(parser.getClass().getSimpleName(), parser.parse()));
    }

    @Override
    public HashMap<String, String> getBankInfo() {
        return info;
    }
}
