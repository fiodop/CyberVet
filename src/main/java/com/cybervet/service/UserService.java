package com.cybervet.service;

import com.cybervet.model.AppUser;
import com.cybervet.model.Feedback;
import com.cybervet.model.Pet;
import com.cybervet.repositry.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Метод для регистрации нового пользователя в бд, если до этого он не запускал бота
     * @param update - объект класса Update из Telegram bot api
     */
    @Transactional
    public void register(Update update) {
        long telegramId = update.getMessage().getFrom().getId();
        String username = update.getMessage().getFrom().getUserName();
        long chatId = update.getMessage().getChatId();
        System.out.println("Username : " + username);
        AppUser user = new AppUser(
                username,
                telegramId,
                chatId,
                new ArrayList<Pet>(),
                new ArrayList<Feedback>()

        );
        userRepository.save(user);
        System.out.println();
        System.out.println("Сохранен пользователь: " + user);
    }
}
