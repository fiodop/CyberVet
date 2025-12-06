package com.cybervet.service;

import com.cybervet.model.enums.UserState;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
@Service
public class StateService {
    private final Map<Long, UserState> states = new HashMap<Long, UserState>();

    public UserState getState(long chatId) {
        return states.getOrDefault(chatId, UserState.nothing);

    }

    public void setState(long chatId, UserState state) {
        states.put(chatId, state);
    }

    public boolean isNewUser(long chatId) {
        return getState(chatId) == null;
    }
}

