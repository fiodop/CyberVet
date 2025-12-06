package com.cybervet.dispatcher;

import com.cybervet.handler.commandHandler.CommandHandler;
import com.cybervet.model.dto.AppUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandDispatcher {
    private final List<CommandHandler> handlers;

    public AppUserResponseDto dispatch(String command, long chatId, Update update) {
        AppUserResponseDto appUserResponseDto = new AppUserResponseDto(chatId, "Неизвестная команда");

        return handlers.stream()
                .filter(h -> h.supports(command))
                .findFirst()
                .map(h -> h.handle(command, chatId, update))
                .orElse(appUserResponseDto);
    }
}
