package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.keyboard.InlineKeyboardService;
import com.cybervet.service.keyboard.ReplyKeyboardService;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
@HandlerForState(UserState.ASKING_ACTIVITY)
public class PetActivityHandler implements MessageHandler {
    private final StateService stateService;
    private final ReplyKeyboardService replyKeyboardService;
    private final InlineKeyboardService inlineKeyboardService;


    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        ArrayList<ResponseDto> responses = new ArrayList<>();
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        try{
            setWeight(chatId, message);
        } catch (Exception e){
            response.setMessage("Введите число — вес питомца");
            responses.add(response);
            return responses;
        }
        response.setMessage("Выберите активность питомца");
        response.setReplyKeyboardMarkup(replyKeyboardService.getActivityKeyboard());
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());
        responses.add(response);
        stateService.setState(chatId, UserState.CHOOSING_PHYSIOLOGICAL_STATE);
        return responses;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }

    private void setWeight(long chatId, String message) {
        double weight = Double.parseDouble(message);
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);
        pet.setWeight(weight);
        pets.put(chatId, pet);
    }
}
