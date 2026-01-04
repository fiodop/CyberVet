package com.cybervet.handler.messageHandler;
import com.cybervet.model.AppUser;
import com.cybervet.model.Pet;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.service.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MyPetsPressedHandler implements MessageHandler {
    private final UserService userService;
    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        AppUser user = userService.getUserByChatId(chatId);
        ArrayList<Pet> pets = (ArrayList<Pet>) user.getPetList();
        ArrayList<ResponseDto> responses = new ArrayList<>();
        if(!pets.isEmpty()){
            ResponseDto response = new ResponseDto();
            response.setChatId(chatId);
            response.setMessage("У вас нет добавленных питомцев");
            responses.add(response);
            return responses;
        }

        
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Мои питомцы");
    }
}
