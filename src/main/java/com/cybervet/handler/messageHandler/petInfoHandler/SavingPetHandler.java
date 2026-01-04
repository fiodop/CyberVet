package com.cybervet.handler.messageHandler.petInfoHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.PhysiologicalState;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.model.PetService;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@HandlerForState(UserState.SAVING_PET)
@RequiredArgsConstructor
public class SavingPetHandler implements MessageHandler {
    private final StateService stateService;
    private final PetService petService;

    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        setPhysiologicalState(chatId, message);



        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setMessage("Ваш питомец сохранен");
        stateService.setState(chatId, UserState.MAIN_MENU);

        ArrayList<ResponseDto> responses = new ArrayList<>();
        responses.add(response);
        return responses;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Беременная")
                || message.equals("Кастрирован(а)")
                || message.equals("Нормальное состояние");
    }

    private void setPhysiologicalState(long chatId, String physiologicalState) {
        var pets = stateService.getPets();
        PetDto pet = pets.get(chatId);

        switch (physiologicalState) {
            case "Беременная":
                pet.setPhysiologicalState(PhysiologicalState.PREGNANT);
                break;

            case "Кастрирован(а)":
                pet.setPhysiologicalState(PhysiologicalState.CASTRATED);
                break;

            case "Нормальное состояние":
                pet.setPhysiologicalState(PhysiologicalState.NORMAL_STATE);
                break;

            default: throw new IllegalArgumentException();
        }

        petService.save(pet);
        pets.remove(chatId);
    }




}
