package com.cybervet.dispatcher;

import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageDispatcher {
    private final StateService stateService;
    private final Map<UserState, MessageHandler> handlers;


    public AppUserResponseDto dispatch(long chatId, String message) {
        UserState state = stateService.getState(chatId);

        MessageHandler handler = handlers.get(state);

        if(handler != null) {
            handler.handle(chatId, message);
        }

        AppUserResponseDto appUserResponseDto = new AppUserResponseDto(chatId, message);
        appUserResponseDto.setMessage("Неизвестная команда");
        return appUserResponseDto;
    }
}
