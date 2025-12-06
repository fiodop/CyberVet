package com.cybervet.service;

import com.cybervet.model.AppUser;
import com.cybervet.repositry.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Метод для регистрации нового пользователя в бд, если до этого он не запускал бота
     * @param appUser - объект класса User, который содержит данные пользователя
     */
    public void register(AppUser appUser) {
        if(userRepository.findByUsername(appUser.getUsername()).isEmpty()){
            userRepository.save(appUser);
        }
    }
}
