package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.PetAge;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.keyboard.InlineKeyboardService;
import com.cybervet.service.keyboard.ReplyKeyboardService;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@HandlerForState(UserState.ASKING_WEIGHT)
@RequiredArgsConstructor
public class PetWeightHandler implements MessageHandler {

    private final StateService stateService;
    private final InlineKeyboardService inlineKeyboardService;
    private final ReplyKeyboardService replyKeyboardService;

    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        ArrayList<ResponseDto> responses = new ArrayList<>();
        switch (message) {
            case "До 2 месяцев" -> setAge(chatId, PetAge.BEFORE_2_MONTHS);
            case "До года" -> setAge(chatId, PetAge.BEFORE_1_YEAR);
            case "До 5 лет" -> setAge(chatId, PetAge.BEFORE_5_YEARS);
            case "Старше 5 лет" -> setAge(chatId, PetAge.OLDER_5_YEARS);
            default -> {
                response.setMessage("Выберите число — возраст питомца");
                responses.add(response);
                return responses;
            }
        }
        stateService.setState(chatId, UserState.ASKING_ACTIVITY);

        response.setMessage("Введите вес питомца в килограммах");
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());
        responses.add(response);
        return responses;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }

    private void setAge(long chatId, PetAge age) {
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);
        pet.setAge(age);
        pets.put(chatId, pet);
    }
}

