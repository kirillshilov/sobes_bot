package com.shilov.sobes_bot.controller;

import com.shilov.sobes_bot.service.UserService;
import com.shilov.sobes_bot.service.UtilBotService;
import com.shilov.sobes_bot.config.BotConfig;
import com.shilov.sobes_bot.service.java.AbstractJavaQuestionProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;


@Component
@RequiredArgsConstructor
@Slf4j
public class SobesBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final Map<String, AbstractJavaQuestionProcessor> processors;
    private final UserService userService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = UtilBotService.getChatId(update);
        if (chatId == null) {
            log.warn("Айди чата отсутствует в сообщении");
            throw new RuntimeException("Айди чата отсутствует в сообщении");
        }
        SendMessage message;
        userService.getUserByIdOrCreateUser(chatId);
        if (!update.hasCallbackQuery()) {
            message = processors.get("java_default").proceed(update,chatId);
            sendMessageToUser(message);
            return;
        }
        AbstractJavaQuestionProcessor processor;
        if (update.hasCallbackQuery() && update.getCallbackQuery().getData() != null) {
            processor = processors.get(update.getCallbackQuery().getData());
        } else {
            processor = processors.get("java_default");
        }
        message = processor.proceed(update,chatId);
        sendMessageToUser(message);

    }

    public void sendMessageToUser(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.warn(e.toString());
        }
    }
}
