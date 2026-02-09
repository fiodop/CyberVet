package com.cybervet.handler.commandHandler;

import com.cybervet.model.dto.ResponseDto;
import com.cybervet.service.exel.ExelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileInputStream;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class ExelTableHandler implements CommandHandler {
    private final ExelService exelService;

    @Override
    public boolean supports(String command) {
        return command.equals("/exel");
    }

    @Override
    public ResponseDto handle(String command, long chatId, Update update) {
        InputStream table;
        try {
            table = new FileInputStream("src/main/resources/exel/тест 1.xlsx");
            int cnt = exelService.readExel(table);
            return new ResponseDto(chatId, "Успешно загружено " + cnt + " строк");
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseDto(chatId, "Не удалось загрузить документ");

    }
}
