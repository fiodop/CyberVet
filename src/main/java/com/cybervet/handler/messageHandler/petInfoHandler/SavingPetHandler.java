package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.ActivityLevel;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.InlineKeyboardService;
import com.cybervet.service.ReplyKeyboardService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.SAVING_PET)
@RequiredArgsConstructor
public class SavingPetHandler implements MessageHandler {
    private final StateService stateService;
    private final ReplyKeyboardService replyKeyboardService;
    private final InlineKeyboardService inlineKeyboardService;

    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        AppUserResponseDto response = new AppUserResponseDto();
        response.setChatId(chatId);

        try{
            setActivity(chatId, message);
        } catch (Exception e) {
            response.setMessage("Выберите ниже активность питомца");
            return response;
        }

        response.setMessage("Ваш питомец сохранен");
        stateService.
    }

    @Override
    public boolean supports(String message) {
        return false;
    }

    private void setActivity(long chatId, String activity) throws Exception {
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);

        switch (activity){
            case "Высокая" :
                pet.setActivityLevel(ActivityLevel.HIGH);
                break;

            case "Средняя":
                pet.setActivityLevel(ActivityLevel.MEDIUM);
                break;

            case "Низкая":
                pet.setActivityLevel(ActivityLevel.LOW);
                break;

            default:
                throw new Exception();
        }


    }
}
