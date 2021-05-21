package com.filos.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filos.configs.SpringConfig;
import com.filos.user.CreateUser;
import com.filos.user.FindUser;
import com.filos.user.UserLogin;

@Configuration
public class Config {
    private final SpringConfig config = new SpringConfig();

    @Bean
    public CreateUser createUser() {
        return config.createUser();
    }

    @Bean
    public FindUser findUser() {
        return config.findUser();
    }

    @Bean
    public UserLogin loginUser() {
        return config.loginUser();
    }

}
