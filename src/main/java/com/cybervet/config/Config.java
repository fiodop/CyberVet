package com.cybervet.config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "bot")
@Data
public class Config {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.key}")
    private String token;


}
