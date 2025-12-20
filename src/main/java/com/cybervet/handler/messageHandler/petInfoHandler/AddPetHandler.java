package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.InlineKeyboardService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@HandlerForState(UserState.ADDING_PET)
@RequiredArgsConstructor
public class AddPetHandler implements MessageHandler {
    private final StateService stateService;
    private final InlineKeyboardService inlineKeyboardService;

    @Override
    public ResponseDto handle(long chatId, String message) {
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setMessage("Отправьте кличку своего питомца");
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());
        stateService.setState(chatId, UserState.ASKING_PET_TYPE);
        return response;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Добавить питомца");
    }


}
