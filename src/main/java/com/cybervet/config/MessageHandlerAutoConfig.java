package com.cybervet.config;

import com.cybervet.annotation.HandlerForState;
import com.cybervet.handler.messageHandler.MessageHandler;
import com.cybervet.model.enums.UserState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MessageHandlerAutoConfig {
    @Bean
    public Map<UserState, MessageHandler> messageHandlers(List<MessageHandler> handlers){
        Map<UserState, MessageHandler> map = new HashMap<>();

        for(MessageHandler handler : handlers){
            HandlerForState annotation = handler.getClass().getAnnotation(HandlerForState.class);

            if(annotation != null){
                map.put(annotation.value(), handler);
            }
        }

        return map;
    }
}
