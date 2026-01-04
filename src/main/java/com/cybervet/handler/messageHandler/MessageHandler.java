package com.cybervet.handler.messageHandler;

import com.cybervet.model.dto.ResponseDto;

import java.util.ArrayList;

public interface MessageHandler {
    ArrayList<ResponseDto> handle(long chatId, String message);
    boolean supports(String message);
}
