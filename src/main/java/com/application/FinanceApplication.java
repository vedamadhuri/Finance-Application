package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class FinanceApplication {
    private static final Logger logger = LoggerFactory.getLogger(FinanceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
        logger.info("Main Class is just started");

    }
}
