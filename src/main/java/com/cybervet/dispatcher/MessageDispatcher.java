package com.cybervet.dispatcher;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageDispatcher {

    private final StateService stateService;
    private final List<MessageHandler> handlers;

    public AppUserResponseDto dispatch(long chatId, String message) {
        UserState state = stateService.getState(chatId);

        for (MessageHandler handler : handlers) {
            if (handler.supports(message)) {
                return handler.handle(chatId, message);
            }
        }

        for (MessageHandler handler : handlers) {
            HandlerForState annotation = handler.getClass().getAnnotation(HandlerForState.class);
            if (annotation != null && annotation.value() == state) {
                return handler.handle(chatId, message);
            }
        }

        AppUserResponseDto unknown = new AppUserResponseDto();
        unknown.setChatId(chatId);
        unknown.setMessage("Неизвестная команда");
        return unknown;
    }
}
