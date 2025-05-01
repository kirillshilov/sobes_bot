package com.shilov.sobes_bot.service.java;

import com.shilov.sobes_bot.service.AbstractUpdateProcessor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface AbstractJavaQuestionProcessor extends AbstractUpdateProcessor {
SendMessage proceed(Update update,Long chatId);
}
