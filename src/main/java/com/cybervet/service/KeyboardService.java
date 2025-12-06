package com.cybervet.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardService {

    /**
     * @return экземпляр класса ReplyKeyboardMarkup с кнопками основного меню
     */
    public ReplyKeyboardMarkup getMainMenuReplyKeyboard() {
        ArrayList<String> buttons = new ArrayList<>();
        buttons.add("Создать рацион");
        buttons.add("Мои рационы");
        buttons.add("Оставить отзыв");
        buttons.add("Добавить питомца");

        return getReplyKeyboardMarkup(buttons, 2);
    }

    public ReplyKeyboardMarkup getTypesOfAnimalsKeyboard() {
        ArrayList<String> buttons = new ArrayList<>();
        buttons.add("Кошка");
        buttons.add("Собака");

        getReplyKeyboardMarkup(buttons, 2);
        return getReplyKeyboardMarkup(buttons, 2);
    }



    /**
     *  Метод, который создает экземпляр класса ReplyKeyboardMarkup с нужными кнопками
     * @param buttons список кнопок
     * @param maxButtonPerRow максимальное число кнопок в ряду
     * @return клавиатуру с кнопками
     */
    private ReplyKeyboardMarkup getReplyKeyboardMarkup (List <String> buttons,int maxButtonPerRow){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow buttonsRow = new KeyboardRow();

        int cnt = 0;
        for (int i = 0; i < buttons.size(); i++) {
            buttonsRow.add(buttons.get(i));
            cnt++;

            if (cnt == maxButtonPerRow) {
                keyboardRows.add(buttonsRow);
                buttonsRow = new KeyboardRow();
                cnt = 0;
            }
        }

        if (!buttonsRow.isEmpty()) {
            keyboardRows.add(buttonsRow);
        }

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }
}
