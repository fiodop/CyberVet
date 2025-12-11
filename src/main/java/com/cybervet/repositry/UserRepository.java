package com.cybervet.repositry;

import com.cybervet.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByTelegramId(long telegramId);

    AppUser getAppUserByChatId(long chatId);
}
