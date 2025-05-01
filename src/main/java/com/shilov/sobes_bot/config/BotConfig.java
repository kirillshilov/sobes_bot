package com.shilov.sobes_bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration

@Data
@PropertySource(value = "application.properties")
public class BotConfig {
    @Value("${bot.names}")
    String botName;
    @Value("${bot.tokens}")
    String token ;
}