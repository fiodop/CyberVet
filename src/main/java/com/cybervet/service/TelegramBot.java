package com.cybervet.service;

import com.cybervet.config.Config;
import com.cybervet.dispatcher.CommandDispatcher;
import com.cybervet.dispatcher.MessageDispatcher;
import com.cybervet.model.dto.AppUserResponseDto;
import com.cybervet.model.enums.UserState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final Config config;
    private final CommandDispatcher commandDispatcher;
    private final MessageDispatcher messageDispatcher;



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
        if (!update.hasMessage() || !update.getMessage().hasText())
            return;

        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (message.startsWith("/")) {
            AppUserResponseDto response = commandDispatcher.dispatch(message, chatId, update);
            sendMessage(response);
            return;
        }


        AppUserResponseDto response = messageDispatcher.dispatch(chatId, message);
        sendMessage(response);
    }

    public void sendMessage(AppUserResponseDto dto) {
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
