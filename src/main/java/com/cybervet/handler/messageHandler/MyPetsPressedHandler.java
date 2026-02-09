package com.cybervet.handler.messageHandler;
import com.cybervet.model.User;
import com.cybervet.model.Pet;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.service.keyboard.InlineKeyboardService;
import com.cybervet.service.model.PetService;
import com.cybervet.service.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MyPetsPressedHandler implements MessageHandler {
    private final PetService petService;
    private final InlineKeyboardService inlineKeyboardService;


    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        ArrayList<ResponseDto> responses = new ArrayList<>();

        ArrayList<Pet> pets = new ArrayList<>(petService.getPets(chatId));
        for (Pet pet : pets) {
            ResponseDto responseDto = new ResponseDto();
            if(pet.getDiet() == null) {
                responseDto.setInlineKeyboardMarkup(inlineKeyboardService.createDiet());
            }
            responses.add(responseDto);
        }

        if(pets.isEmpty()){
            ResponseDto response = new ResponseDto();
            response.setChatId(chatId);
            response.setMessage("У вас нет добавленных питомцев");
            responses.add(response);
            return responses;
        }
        return responses;
        
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Мои питомцы");
    }
}
