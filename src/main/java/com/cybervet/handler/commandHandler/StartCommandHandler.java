package com.cybervet.handler.commandHandler;

import com.cybervet.handler.messageHandler.MainMenuHandler;
import com.cybervet.model.AppUser;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.KeyboardService;
import com.cybervet.service.StateService;
import com.cybervet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {
    private final StateService stateService;
    private final UserService userService;


    @Override
    public boolean supports(String command) {
        return command.equals("/start");
    }

    @Override
    public AppUserResponseDto handle(String command, long chatId, Update update) {
        AppUserResponseDto response = registerUser(update);
        KeyboardService keyboardService = new KeyboardService();
        response.setReplyKeyboardMarkup(keyboardService.getMainMenuReplyKeyboard());

        stateService.setState(chatId, UserState.MAIN_MENU);

        return response;
    }


    private AppUserResponseDto registerUser(Update update) {
        AppUser appUser = new AppUser();
        appUser.setTelegramId(update.getMessage().getFrom().getId());

        userService.register(appUser);

        return new AppUserResponseDto(update.getMessage().getChatId(),"Привет! Это бот CyberVet!");

    }


}
