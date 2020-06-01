package com.fzn.pesystem.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder(){
        System.out.println("被加载");
        return new BCryptPasswordEncoder();
    }
}
