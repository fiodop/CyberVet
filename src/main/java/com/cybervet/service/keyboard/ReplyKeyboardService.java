package com.cybervet.service.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyKeyboardService {

    public ReplyKeyboardMarkup getAgeKeyboard() {
        ArrayList<String> age = new ArrayList<>();
        age.add("До 2 месяцев");
        age.add("До года");
        age.add("До 5 лет");
        age.add("Старше 5 лет");
        return createReplyKeyboardMarkup(age, 1);
    }

    public ReplyKeyboardMarkup getPhysiologicalStateKeyboard(){
        ArrayList<String> states = new ArrayList<>();
        states.add("Беременная");
        states.add("Кастрирован(а)");
        states.add("Нормальное состояние");

        return createReplyKeyboardMarkup(states, 1);
    }

    public ReplyKeyboardMarkup getDogBreedKeyboard() {
        ArrayList<String> breeds = new ArrayList<>();
        breeds.add("Маленькая");
        breeds.add("Средняя");
        breeds.add("Крупная");
        return createReplyKeyboardMarkup(breeds, 2);
    }

    public ReplyKeyboardMarkup getCatBreedKeyboard() {
        ArrayList<String> breeds = new ArrayList<>();
        breeds.add("Маленькая");
        breeds.add("Средняя");
        breeds.add("Крупная");
        return createReplyKeyboardMarkup(breeds, 2);
    }



    /**
     * @return экземпляр класса ReplyKeyboardMarkup с кнопками главного меню
     */
    public ReplyKeyboardMarkup getMainMenuReplyKeyboard() {
        ArrayList<String> buttons = new ArrayList<>();

        buttons.add("Оставить отзыв");
        buttons.add("Мои питомцы");

        return createReplyKeyboardMarkup(buttons, 1);
    }

    public ReplyKeyboardMarkup getTypesOfAnimalsKeyboard() {
        ArrayList<String> buttons = new ArrayList<>();
        buttons.add("\uD83D\uDC08Кошка");
        buttons.add("\uD83D\uDC15Собака");

        return createReplyKeyboardMarkup(buttons, 2);
    }

    public ReplyKeyboardMarkup getActivityKeyboard() {
        ArrayList<String> buttons = new ArrayList<>();
        buttons.add("Низкая");
        buttons.add("Средняя");
        buttons.add("Высокая");

        return createReplyKeyboardMarkup(buttons, 3);
    }


    /**
     *  Метод, который создает экземпляр класса ReplyKeyboardMarkup с нужными кнопками
     * @param buttons список кнопок
     * @param maxButtonPerRow максимальное число кнопок в ряду
     * @return клавиатуру с кнопками
     */
    private ReplyKeyboardMarkup createReplyKeyboardMarkup(List <String> buttons, int maxButtonPerRow){
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
