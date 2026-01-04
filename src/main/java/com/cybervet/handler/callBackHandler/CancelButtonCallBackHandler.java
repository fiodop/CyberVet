package com.cybervet.handler.callBackHandler;

import com.cybervet.model.dto.ResponseDto;
import com.cybervet.model.enums.UserState;
import com.cybervet.service.keyboard.ReplyKeyboardService;
import com.cybervet.service.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CancelButtonCallBackHandler implements CallBackHandler {
    private final StateService stateService;
    private final ReplyKeyboardService replyKeyboardService;
    @Override
    public boolean supports(String callback) {
        return callback.equals("CANCEL_BUTTON");
    }

    @Override
    public ResponseDto handle(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        stateService.setState(chatId, UserState.MAIN_MENU);
        ResponseDto responseDto = new ResponseDto(chatId, "Выберите действие");
        responseDto.setReplyKeyboardMarkup(replyKeyboardService.getMainMenuReplyKeyboard());
        return responseDto;
    }
}
