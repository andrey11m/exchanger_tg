package com.java.exchanger_tg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangerTgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangerTgApplication.class, args);
    }
}
