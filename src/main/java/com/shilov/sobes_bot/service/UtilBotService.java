package com.shilov.sobes_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class UtilBotService {
    public static Long getChatId (Update update){
        Long chatId;
        if (update.hasMessage() && update.getMessage().getChatId() != null){
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery() &&
                update.getCallbackQuery().getMessage() != null &&
                update.getCallbackQuery().getMessage().getChatId() != null) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        else return null;
    }
}
