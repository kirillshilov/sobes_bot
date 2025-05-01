package com.shilov.sobes_bot.service.question;

import com.shilov.sobes_bot.exception.QuestionException;
import com.shilov.sobes_bot.model.Question;
import com.shilov.sobes_bot.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JavaQuestionService implements AbstractQuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Question getRandomQuestion() {
        long count = questionRepository.count();
        if (count == 0) {
            throw new QuestionException("No question found");
        }
        Random random = new Random();
        Long randomId = random.nextLong(count);
        return questionRepository.findById(randomId).orElseThrow(() -> new QuestionException("repository Exception"));
    }
}
