package com.cybervet.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
@Getter
@Setter
public class AppUserResponseDto {
    private long chatId;
    private String message;
    private ReplyKeyboardMarkup replyKeyboardMarkup;
    private InlineKeyboardMarkup inlineKeyboardMarkup;

    public AppUserResponseDto(long chatId, String message){
        this.chatId = chatId;
        this.message = message;
    }

    public AppUserResponseDto(){
    }
}
