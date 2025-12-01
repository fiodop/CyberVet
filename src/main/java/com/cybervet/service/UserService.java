package com.cybervet.service;

import com.cybervet.model.User;
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
     * @param user - объект класса User, который содержит данные пользователя
     */
    public void register(User user) {
        if(userRepository.findByUsername(user.getUsername()).isEmpty()){
            userRepository.save(user);
        }
    }
}
