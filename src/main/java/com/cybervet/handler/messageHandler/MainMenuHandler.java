package com.cybervet.handler.messageHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.keyboard.ReplyKeyboardService;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@HandlerForState(UserState.MAIN_MENU)
@RequiredArgsConstructor
public class MainMenuHandler implements MessageHandler {
    private final ReplyKeyboardService replyKeyboardService;

    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setMessage("Выберите действие");
        response.setReplyKeyboardMarkup(
                replyKeyboardService.getMainMenuReplyKeyboard());
        ArrayList<ResponseDto> responses = new ArrayList<>();
        responses.add(response);
        return responses;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }
}
