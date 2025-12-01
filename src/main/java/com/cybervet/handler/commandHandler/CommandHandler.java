package com.cybervet.handler.commandHandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface CommandHandler {
    boolean supports(String command);
    String handle(String command, long chatId, Update update);

}
