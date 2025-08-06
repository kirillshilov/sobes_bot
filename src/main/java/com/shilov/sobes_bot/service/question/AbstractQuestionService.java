package com.shilov.sobes_bot.service.question;

import com.shilov.sobes_bot.model.Question;

public interface AbstractQuestionService {
    public Question getNextQuestion (Long number);
    public Question getQuestionByNumber (Long number);
}
