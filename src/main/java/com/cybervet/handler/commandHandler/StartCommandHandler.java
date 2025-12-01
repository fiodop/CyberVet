package com.cybervet.handler.commandHandler;

import com.cybervet.model.User;
import com.cybervet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {
    private final UserService userService;

    @Override
    public boolean supports(String command) {
        return command.equals("/start");
    }

    @Override
    public String handle(String command, long chatId, Update update) {
        registerUser(update);
        return "Привет! Это бот CyberVet!";
    }
    private User registerUser(Update update) {
        User user = new User();
        user.setUsername(update.getMessage().getFrom().getUserName());

        userService.register(user);
    }
}
