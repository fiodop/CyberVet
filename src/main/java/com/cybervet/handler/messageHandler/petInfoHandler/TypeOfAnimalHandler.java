package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.InlineKeyboardService;
import com.cybervet.service.ReplyKeyboardService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.ASKING_PET_TYPE)
@RequiredArgsConstructor
public class TypeOfAnimalHandler implements MessageHandler {
    private final StateService stateService;
    private final ReplyKeyboardService replyKeyboardService;
    private final InlineKeyboardService inlineKeyboardService;

    @Override
    public AppUserResponseDto handle(long chatId, String message) {

        setName(chatId, message);
        AppUserResponseDto response = new AppUserResponseDto();
        response.setChatId(chatId);
        response.setMessage("Выберите вид животного:");
        response.setReplyKeyboardMarkup(replyKeyboardService.getTypesOfAnimalsKeyboard());
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());
        stateService.setState(chatId, UserState.CHOOSING_BREED);
        return response;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }

    private void setName(long chatId, String name) {
        PetDto pet = new PetDto();
        pet.setChatId(chatId);
        pet.setName(name);
        HashMap <Long, PetDto> pets = stateService.getPets();
        pets.put(chatId, pet);

    }


}
