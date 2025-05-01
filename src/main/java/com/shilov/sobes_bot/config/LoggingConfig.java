package com.shilov.sobes_bot.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @PostConstruct
    public void setLogLevel() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Отключаем подробный лог TelegramBots
        context.getLogger("org.telegram").setLevel(Level.WARN);
        context.getLogger("org.telegram.telegrambots").setLevel(Level.WARN);
        context.getLogger("org.telegram.telegrambots.meta").setLevel(Level.WARN);
    }
}

