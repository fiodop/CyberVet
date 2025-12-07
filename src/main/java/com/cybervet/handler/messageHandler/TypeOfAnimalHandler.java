package com.cybervet.handler.messageHandler;

import com.cybervet.model.dto.AppUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TypeOfAnimalHandler implements MessageHandler {

    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        return null;
    }

    @Override
    public boolean supports(String message) {
        return false;
    }
}
