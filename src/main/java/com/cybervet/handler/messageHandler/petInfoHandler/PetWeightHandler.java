package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.InlineKeyboardService;
import com.cybervet.service.ReplyKeyboardService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.ASKING_WEIGHT)
@RequiredArgsConstructor
public class PetWeightHandler implements MessageHandler {

    private final StateService stateService;
    private final InlineKeyboardService inlineKeyboardService;
    private final ReplyKeyboardService replyKeyboardService;

    @Override
    public ResponseDto handle(long chatId, String message) {
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);

        try{
            setWeight(chatId, message);
        } catch (Exception e){
            response.setMessage("Введите число — возраст питомца");
            return response;
        }

        stateService.setState(chatId, UserState.ASKING_ACTIVITY);

        response.setMessage("Введите вес питомца в килограммах");
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());
        return response;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }

    private void setWeight(long chatId, String message) {
        int age = Integer.parseInt(message);
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);
        pet.setAge(age);
        pets.put(chatId, pet);
    }
}

