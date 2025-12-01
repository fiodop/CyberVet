package com.cybervet.dispatcher;

import com.cybervet.handler.commandHandler.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandDispatcher {
    private final List<CommandHandler> handlers;

    public String dispatch(String command, long chatId, Update update) {
        return handlers.stream()
                .filter(h -> h.supports(command))
                .findFirst()
                .map(h -> h.handle(command, chatId, update))
                .orElse("Неизвестная команда");
    }
}
