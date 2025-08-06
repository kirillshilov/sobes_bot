package com.shilov.sobes_bot.service.java;

import com.shilov.sobes_bot.model.Question;
import com.shilov.sobes_bot.model.User;
import com.shilov.sobes_bot.service.KeyboardFactory;
import com.shilov.sobes_bot.service.UserService;
import com.shilov.sobes_bot.service.question.JavaQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramBot;

@Service("/get_question_by_number")
@RequiredArgsConstructor
public class JavaQuestionByNumberProcessor implements AbstractJavaQuestionProcessor {
    private final UserService userService;
    private final JavaQuestionService questionService;

    @Override
    public SendMessage proceed(Update update, Long chatId) {
        User user = userService.getUserByIdOrCreateUser(chatId);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        Long number;
        if (update.hasMessage()) {
            try {
                number = Long.valueOf(update.getMessage().getText());
            } catch (NumberFormatException e) {
                message.setText("Неверный номер вопроса. Попробуйте ввести еще раз: ");
                message.setReplyMarkup(KeyboardFactory.getKeyboardToDefault());
                return message;
            }
            Question question = questionService.getQuestionByNumber(number);
            user.setLastQuestion(question);
            user.setState(null);
            userService.saveUser(user);
            message.setText("№ " + question.getId() + " : "+ question.getText());
            message.setReplyMarkup(KeyboardFactory.getKeyboardWithQuestion());
            return message;
        }
        user.setState("/get_question_by_number");
        userService.saveUser(user);
        message.setText("Введите номер вопроса: ");
        return message;
    }
}
