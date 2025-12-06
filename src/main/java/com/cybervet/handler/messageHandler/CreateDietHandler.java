package com.cybervet.handler.messageHandler;

import com.cybervet.model.dto.AppUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CreateDietHandler implements MessageHandler {


    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        return null;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Создать рацион");
    }
}

//TODO написать дальнейшую логику составления рациона
