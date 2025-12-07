package com.cybervet.handler.messageHandler;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.ReplyKeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@HandlerForState(UserState.CHOOSING_CAT_BREED)
@RequiredArgsConstructor
public class CatButtonPressedHandler implements MessageHandler {
    private final ReplyKeyboardService replyKeyboardService;

    @Override
    public AppUserResponseDto handle(long chatId, String message) {
        AppUserResponseDto response = new AppUserResponseDto();
        response.setChatId(chatId);
        response.setMessage("Какой породы ваш питомец?");
        response.setReplyKeyboardMarkup(replyKeyboardService.getCatBreedsKeyboard());
        return response;
    }

    @Override
    public boolean supports(String message) {
        return message.equals("Кошка");
    }
}
