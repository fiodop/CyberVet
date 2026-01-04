package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.keyboard.InlineKeyboardService;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@HandlerForState(UserState.ASKING_AGE)
@RequiredArgsConstructor
public class PetAgeHandler implements MessageHandler {
    private final StateService stateService;
    private final InlineKeyboardService inlineKeyboardService;


    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        setBreed(chatId, message);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setChatId(chatId);
        responseDto.setMessage("Выберите возраст вашего питомца");
        stateService.setState(chatId, UserState.ASKING_WEIGHT);
        responseDto.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());

        ArrayList<ResponseDto> responses = new ArrayList<>();
        responses.add(responseDto);
        return responses;
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
