package com.cybervet.handler.callBackHandler;

import com.cybervet.model.dto.ResponseDto;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public interface CallBackHandler {
    boolean supports(String callback);
    ResponseDto handle(Update update);
}
