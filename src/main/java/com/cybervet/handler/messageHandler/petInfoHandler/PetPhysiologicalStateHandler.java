package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.ActivityLevel;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.ReplyKeyboardService;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.CHOOSING_PHYSIOLOGICAL_STATE)
@RequiredArgsConstructor
public class PetPhysiologicalStateHandler implements MessageHandler {

    private final StateService stateService;
    private final ReplyKeyboardService replyKeyboardService;
    @Override
    public ResponseDto handle(long chatId, String message) {
        setActivity(chatId, message);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setChatId(chatId);
        responseDto.setMessage("Выберите физиологическое состояние питомца");
        responseDto.setReplyKeyboardMarkup(replyKeyboardService.getPhysiologicalStateKeyboard());
        stateService.setState(chatId, UserState.SAVING_PET);
        return responseDto;


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

        pets.put(chatId, pet);
    }
}
