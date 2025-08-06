package com.shilov.sobes_bot.service.java;

import com.shilov.sobes_bot.model.User;
import com.shilov.sobes_bot.service.KeyboardFactory;
import com.shilov.sobes_bot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service("/cancel")
@RequiredArgsConstructor
public class JavaCancelProcessor implements AbstractJavaQuestionProcessor{
    private final UserService userService;
    @Override
    public SendMessage proceed(Update update,Long chatId) {
        User user = userService.getUserByIdOrCreateUser(chatId);
        SendMessage message = new SendMessage();
        message.setText("Выберите действие:");
        message.setChatId(chatId);
        if (user.getLastQuestion() == null) {
            message.setReplyMarkup(KeyboardFactory.getWithoutQuestionKeyboard());
        }
        else {
            message.setReplyMarkup(KeyboardFactory.getKeyboardWithQuestion());
        }
        return message;
    }
}
