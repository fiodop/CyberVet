package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.ActivityLevel;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.PetService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.SAVING_PET)
@RequiredArgsConstructor
public class SavingPetHandler implements MessageHandler {
    private final StateService stateService;
    private final PetService petService;

    @Override
    public ResponseDto handle(long chatId, String message) {
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        setActivity(chatId, message);
        response.setMessage("Ваш питомец сохранен");
        stateService.setState(chatId, UserState.MAIN_MENU);
        return response;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Высокая")
                || message.equals("Средняя")
                || message.equals("Низкая");
    }

    private void setActivity(long chatId, String activity){
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
        }
        System.out.println(pet);
        petService.save(pet);

    }
}
