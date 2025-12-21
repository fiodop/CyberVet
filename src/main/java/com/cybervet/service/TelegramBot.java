package com.cybervet.service;

import com.cybervet.config.Config;
import com.cybervet.dispatcher.CallBackDispatcher;
import com.cybervet.dispatcher.CommandDispatcher;
import com.cybervet.dispatcher.MessageDispatcher;
import com.cybervet.model.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final Config config;
    private final CommandDispatcher commandDispatcher;
    private final MessageDispatcher messageDispatcher;
    private final CallBackDispatcher callBackDispatcher;



    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Update received: " + update);

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (message.startsWith("/")) {
                ResponseDto response =
                        commandDispatcher.dispatch(message, chatId, update);
                sendMessage(response);
                return;
            }

            ResponseDto response =
                    messageDispatcher.dispatch(chatId, message);
            sendMessage(response);
            return;
        }

        if (update.hasCallbackQuery()) {
            ResponseDto response =
                    callBackDispatcher.dispatch(update);
            sendMessage(response);
        }
    }


    public void sendMessage(ResponseDto dto) {
        SendMessage message = new SendMessage();

        message.setChatId(String.valueOf(dto.getChatId()));
        message.setText(dto.getMessage());

        if (dto.getInlineKeyboardMarkup() != null) {
            message.setReplyMarkup(dto.getInlineKeyboardMarkup());
        }

        if (dto.getReplyKeyboardMarkup() != null) {
            message.setReplyMarkup(dto.getReplyKeyboardMarkup());
        }
        log.info("|chatId: {} |Sending {}", dto.getChatId(), message.getText());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
