package com.cybervet.handler.messageHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.KeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@HandlerForState(UserState.ADDING_PET)
@RequiredArgsConstructor
public class AddPetHandler implements MessageHandler {
    private final KeyboardService keyboardService;

    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        AppUserResponseDto response = new AppUserResponseDto();
        response.setChatId(chatId);
        response.setMessage("Выберите вид животного:");
        response.setReplyKeyboardMarkup(keyboardService.getTypesOfAnimalsKeyboard());
        return response;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Добавить питомца");
    }


}
