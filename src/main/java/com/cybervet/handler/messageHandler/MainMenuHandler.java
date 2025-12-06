package com.cybervet.handler.messageHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@HandlerForState(UserState.MAIN_MENU)
public class MainMenuHandler implements MessageHandler {

    private final StateService stateService;


    @Override
    public ResponseDto handle(long chatId, String message) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Выберите действие");
        responseDto.setChatId(chatId);
        responseDto.setReplyKeyboardMarkup(getMainMenuReplyKeyboardMarkup());
        return responseDto;
    }


    private ReplyKeyboardMarkup getMainMenuReplyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Создать рацион");
        row.add("Мои рационы");

        keyboardRows.add(row);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
