package com.cybervet.handler.messageHandler;

import com.cybervet.model.dto.ResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CreateDietHandler implements MessageHandler {


    @Override
    public ArrayList<ResponseDto> handle(long chatId, String message) {
        return null;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Создать рацион");
    }
}

//TODO написать дальнейшую логику составления рациона
