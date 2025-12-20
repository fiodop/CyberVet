package com.cybervet.dispatcher;

import com.cybervet.handler.callBackHandler.CallBackHandler;
import com.cybervet.model.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CallBackDispatcher {
    private final List<CallBackHandler> handlers;
    public ResponseDto dispatch(Update update) {
        ResponseDto response = new ResponseDto();
        String callBackData = update.getCallbackQuery().getData();

        return handlers.stream()
                .filter(h -> h.supports(callBackData))
                .findFirst()
                .map(h -> h.handle(update))
                .orElse(response);

    }
}
