package com.shilov.sobes_bot.service.java;

import com.shilov.sobes_bot.service.KeyboardFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service ("java_default")
public class DefaultProcessorJava implements AbstractJavaQuestionProcessor {
    @Override
    public SendMessage proceed(Update update,Long chatId) {
        SendMessage message = new SendMessage();
        message.setText("Выберите действие:");
        message.setChatId(chatId);
        message.setReplyMarkup(KeyboardFactory.getWithoutQuestionKeyboard());
        return message;
    }
}
