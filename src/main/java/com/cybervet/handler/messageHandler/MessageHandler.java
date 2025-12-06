package com.cybervet.handler.messageHandler;

import com.cybervet.model.dto.AppUserResponseDto;

public interface MessageHandler {
    AppUserResponseDto handle(long chatId, String message);
    boolean supports(String message);
}
