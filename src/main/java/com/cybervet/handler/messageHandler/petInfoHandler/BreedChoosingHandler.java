package com.cybervet.handler.messageHandler.petInfoHandler;


import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.TypeOfAnimal;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.keyboard.InlineKeyboardService;
import com.cybervet.service.keyboard.ReplyKeyboardService;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@HandlerForState(UserState.CHOOSING_BREED)
@RequiredArgsConstructor
public class BreedChoosingHandler implements MessageHandler {
    private final ReplyKeyboardService replyKeyboardService;
    private final StateService stateService;
    private final InlineKeyboardService inlineKeyboardService;

    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        ArrayList<ResponseDto> responses = new ArrayList<>();
        if (!message.equals("🐕Собака") && !message.equals("🐈Кошка")) {
            ResponseDto wrong = new ResponseDto();
            wrong.setChatId(chatId);
            wrong.setMessage("Пожалуйста, выберите один из вариантов ниже");
            responses.add(wrong);
            return responses;
        }

        setType(chatId, message);

        ResponseDto response = getResponse(chatId, message);
        responses.add(response);
        stateService.setState(chatId, UserState.ASKING_AGE);

        return responses;
    }

    private ResponseDto getResponse(long chatId, String message) {
        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setInlineKeyboardMarkup(inlineKeyboardService.getCancelButtonKeyboard());
        if (message.equals("🐕Собака")) {
            response.setMessage("Выберите породу собаки:");
            response.setReplyKeyboardMarkup(replyKeyboardService.getDogBreedKeyboard());
        } else {
            response.setMessage("Выберите породу кошки:");
            response.setReplyKeyboardMarkup(replyKeyboardService.getCatBreedKeyboard());
        }
        return response;
    }


    @Override
    public boolean supports(String message) {
        return message.equals("🐕Собака") || message.equals("🐈Кошка");
    }


    private void setType(long chatId, String type) {
        HashMap<Long, PetDto> pets = stateService.getPets();
        PetDto pet = pets.get(chatId);

        if (pet == null) {
            pet = new PetDto();
        }

        if (type.equals("🐕Собака")) {
            pet.setTypeOfAnimal(TypeOfAnimal.DOG);
        } else {
            pet.setTypeOfAnimal(TypeOfAnimal.CAT);
        }

        pets.put(chatId, pet);
    }
}
