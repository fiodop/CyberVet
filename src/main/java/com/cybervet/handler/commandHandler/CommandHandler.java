package com.cybervet.handler.commandHandler;

import com.cybervet.model.dto.ResponseDto;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface CommandHandler {
    boolean supports(String command);
    ResponseDto handle(String command, long chatId, Update update);

}
