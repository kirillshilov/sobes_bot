package com.shilov.sobes_bot.service.java;

import com.shilov.sobes_bot.exception.QuestionException;
import com.shilov.sobes_bot.model.Answer;
import com.shilov.sobes_bot.model.User;
import com.shilov.sobes_bot.repository.AnswerRepository;
import com.shilov.sobes_bot.repository.UserRepository;
import com.shilov.sobes_bot.service.KeyboardFactory;
import com.shilov.sobes_bot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;


@Service("/get_answer")
@RequiredArgsConstructor
@Slf4j
public class JavaAnswerProcessorJava implements AbstractJavaQuestionProcessor {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AnswerRepository answerRepository;

    @Override
    public SendMessage proceed(Update update, Long chatId) {
        User user = userService.getUserByIdOrCreateUser(chatId);
        if (user.getLastQuestion() == null) {
            throw new QuestionException("Сначала нужно получить вопрос");
        }
        if (user.getLastQuestion().getAnswer() == null || user.getLastQuestion().getAnswer().getId() == null) {
            throw new QuestionException("У вопроса отсутствует ответ");
        }
        Optional<Answer> answer = answerRepository.findById(user.getLastQuestion().getAnswer().getId());

        if (answer.isPresent()) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer.get().getText());
        sendMessage.setReplyMarkup(KeyboardFactory.getKeyboardWithQuestion());
        return sendMessage;
        }
        else {
            throw new QuestionException("Ответ не найден по айди");
        }
    }
}
