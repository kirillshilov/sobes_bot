package com.shilov.sobes_bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface AbstractUpdateProcessor {
    SendMessage proceed(Update update, Long chatId);
}
