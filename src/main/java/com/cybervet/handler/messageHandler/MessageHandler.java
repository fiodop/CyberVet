package com.cybervet.handler.messageHandler;

import com.cybervet.model.dto.ResponseDto;

public interface MessageHandler {
    ResponseDto handle(long chatId, String message);
}
