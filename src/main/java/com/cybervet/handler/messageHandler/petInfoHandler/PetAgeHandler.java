package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.InlineKeyboardService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.ASKING_AGE)
@RequiredArgsConstructor
public class PetAgeHandler implements MessageHandler {
    private final StateService stateService;
    private final InlineKeyboardService inlineKeyboardService;


    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        setBreed(chatId, message);

        AppUserResponseDto responseDto = new AppUserResponseDto();
        responseDto.setChatId(chatId);
        responseDto.setMessage("Введите возраст вашего питомца");
        stateService.setState(chatId, UserState.ASKING_WEIGHT);
        responseDto.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());

        return responseDto;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }

    private void setBreed(long chatId, String breed) {
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);
        pet.setBreed(breed);
        pets.put(chatId, pet);
    }

}
