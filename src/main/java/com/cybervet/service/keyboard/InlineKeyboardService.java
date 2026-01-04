package com.cybervet.service.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class InlineKeyboardService {

    public InlineKeyboardMarkup getCreateDiet;

    public InlineKeyboardMarkup getCancelButtonKeyboard(){
        HashMap<String, String> backButton = new HashMap<>();
        backButton.put("Отмена", "CANCEL_BUTTON");

        return getInlineKeyboard(backButton, 1);
    }


    /**
     * Метод для генерации клавиатуры с кнопками под сообщением
     * @param buttons мапа с кнопками, ключ - имя кнопки, значение - идентификатор кнопки
     * @param maxButtonsPerRow - максимальное количество кнопок в строке
     * @return объект класса InlineKeyboardMarkup хранящий в себе клавиатуру с кнопками
     */
    private static InlineKeyboardMarkup getInlineKeyboard (HashMap< String, String > buttons, int maxButtonsPerRow){
        List<List<InlineKeyboardButton>> buttonRows = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        List<String> buttonNames = new ArrayList<>(buttons.keySet());
        int cnt = 0;

        for (int i = 0; i < buttons.size(); i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames.get(i));
            button.setCallbackData(buttons.get(buttonNames.get(i)));
            buttonsRow.add(button);
            cnt++;

            if (cnt == maxButtonsPerRow) {
                buttonRows.add(buttonsRow);
                buttonsRow = new ArrayList<>();
                cnt = 0;
            }
        }

        if (!buttonsRow.isEmpty()) {
            buttonRows.add(buttonsRow);
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(buttonRows);
        return inlineKeyboardMarkup;
    }
}
