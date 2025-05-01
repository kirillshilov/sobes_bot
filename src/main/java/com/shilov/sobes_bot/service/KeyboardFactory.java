package com.shilov.sobes_bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {
    public static InlineKeyboardMarkup getWithoutQuestionKeyboard() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButton = new ArrayList<>();
        List<InlineKeyboardButton> buttonLine1List = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1Line1 = new InlineKeyboardButton();
        inlineKeyboardButton1Line1.setText("Получить вопрос");
        inlineKeyboardButton1Line1.setCallbackData("/get_question");

        buttonLine1List.add(inlineKeyboardButton1Line1);

        allButton.add(buttonLine1List);

        inlineKeyboardMarkup.setKeyboard(allButton);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getKeyboardWithQuestion() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButton = new ArrayList<>();
        List<InlineKeyboardButton> buttonLine1List = new ArrayList<>();
        List<InlineKeyboardButton> buttonLine2List = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1Line1 = new InlineKeyboardButton();
        inlineKeyboardButton1Line1.setText("Получить вопрос");
        inlineKeyboardButton1Line1.setCallbackData("/get_question");
        InlineKeyboardButton inlineKeyboardButton2Line2 = new InlineKeyboardButton();
        inlineKeyboardButton2Line2.setText("Посмотреть ответ");
        inlineKeyboardButton2Line2.setCallbackData("/get_answer");

        buttonLine1List.add(inlineKeyboardButton1Line1);
        buttonLine2List.add(inlineKeyboardButton2Line2);

        allButton.add(buttonLine1List);
        allButton.add(buttonLine2List);

        inlineKeyboardMarkup.setKeyboard(allButton);
        return inlineKeyboardMarkup;
    }
}
