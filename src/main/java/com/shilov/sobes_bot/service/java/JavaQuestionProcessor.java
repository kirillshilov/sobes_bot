package com.shilov.sobes_bot.service.java;

import com.shilov.sobes_bot.model.Question;
import com.shilov.sobes_bot.model.User;
import com.shilov.sobes_bot.service.KeyboardFactory;
import com.shilov.sobes_bot.service.UserService;
import com.shilov.sobes_bot.service.question.JavaQuestionService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service("/get_question")
@RequiredArgsConstructor
public class JavaQuestionProcessor implements AbstractJavaQuestionProcessor {
    private final JavaQuestionService questionService;
    private final UserService userService;

    @Override
    public SendMessage proceed(Update update, Long chatId) {
        User user = userService.getUserByIdOrCreateUser(chatId);
        Question question;
        if (user.getLastQuestion() == null) {
            question = questionService.getNextQuestion(0L);
        }
        else {
            question = questionService.getNextQuestion(user.getLastQuestion().getId());
        }
        user.setLastQuestion(question);
        userService.saveUser(user);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("№ " + question.getId() + " : "+ question.getText());
        message.setReplyMarkup(KeyboardFactory.getKeyboardWithQuestion());
        return message;
    }
}
