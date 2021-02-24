package com.amarilla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@EnableScheduling
@Configuration
public class Config {

    @Bean
    public PasswordEncoder createEncoder() {
        int strength = 12;
        SecureRandom random = new SecureRandom();
        return new BCryptPasswordEncoder(strength, random);
    }
}