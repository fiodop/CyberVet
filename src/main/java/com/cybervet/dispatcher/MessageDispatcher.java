package com.cybervet.dispatcher;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDispatcher {

    private final StateService stateService;
    private final List<MessageHandler> handlers;

    public ArrayList<ResponseDto> dispatch(long chatId, String message) {
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

        ResponseDto unknown = new ResponseDto();
        unknown.setChatId(chatId);
        unknown.setMessage("Неизвестная команда");
        ArrayList<ResponseDto> responses = new ArrayList<>();
        responses.add(unknown);
        return responses;
    }
}
