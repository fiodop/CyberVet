package com.cybervet.handler.commandHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.model.AppUser;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.UserState;
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
    public ResponseDto handle(String command, long chatId, Update update) {
         stateService.setState(chatId, UserState.MAIN_MENU);

         return registerUser(update);

    }
    private ResponseDto registerUser(Update update) {
        AppUser appUser = new AppUser();
        appUser.setTelegramId(update.getMessage().getFrom().getId());

        userService.register(appUser);
        return new ResponseDto(update.getMessage().getChatId(),"Привет! Это бот CyberVet!");

    }

//    private void mainMenu
}
