package com.cybervet.service;

import com.cybervet.model.AppUser;
import com.cybervet.repositry.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Метод для регистрации нового пользователя в бд, если до этого он не запускал бота
     * @param update - объект класса Update из Telegram bot api
     */
    public void register(Update update) {
        long telegramId = update.getMessage().getFrom().getId();
        AppUser user = new AppUser(
                update.getMessage().getFrom().getUserName(),
                telegramId,
                update.getMessage().getChatId()

        );
        userRepository.save(user);
        System.out.println();
        System.out.println("Сохранен пользователь: " + user);
    }
}
