package com.cybervet.handler.messageHandler.petInfoHandler;


import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.Type;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@HandlerForState(UserState.CHOOSING_BREED)
@RequiredArgsConstructor
public class BreedChoosingHandler implements MessageHandler {
    private final StateService stateService;

    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        if(!message.equals("\uD83D\uDC15Собака") || !message.equals("\uD83D\uDC08Кошка")){
            stateService.setState(chatId, UserState.ASKING_PET_TYPE);
            AppUserResponseDto responseDto = new AppUserResponseDto();
            responseDto.setChatId(chatId);
            responseDto.setMessage("Выберите вид животного(нажмите на кнопку):");
        }
        AppUserResponseDto response = new AppUserResponseDto();
        response.setChatId(chatId);

        createPet(chatId, message);
        if(message.equals("\uD83D\uDC15Собака")){
            stateService.setState(chatId, UserState.CHOOSING_DOG_BREED);


        } else {
            stateService.setState(chatId, UserState.SHOOSING_CAT_BREED);
        }
        return response;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("\uD83D\uDC15Собака") || message.equals("\uD83D\uDC08Кошка");
    }

    private void createPet(long chatId, String type) {
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);

        if(type.equals("\uD83D\uDC15Собака")){
            pet.setType(Type.DOG);
        } else {
            pet.setType(Type.CAT);
        }

        pets.put(chatId, pet);
    }
}
