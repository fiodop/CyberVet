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
@RequiredArgsConstructor
@HandlerForState(UserState.ASKING_ACTIVITY)
public class PetActivityHandler implements MessageHandler {
    private final StateService stateService;
    private final ReplyKeyboardService replyKeyboardService;
    private final InlineKeyboardService inlineKeyboardService;


    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        AppUserResponseDto response = new AppUserResponseDto();
        response.setChatId(chatId);
        try{
            setWeight(chatId, message);
        } catch (Exception e){
            response.setMessage("Введите число — возраст питомца");
            return response;
        }
        response.setMessage("Выберите активность питомца");
        response.setReplyKeyboardMarkup(replyKeyboardService.getActivityKeyboard());
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());

        stateService.setState(chatId, UserState.SAVING_PET);
        return response;
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
