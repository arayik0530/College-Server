package com.lnTime.config;

import com.lnTime.api.ExceptionHandlerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandlerControllerProvider {

    @Bean
    public ExceptionHandlerController provide(){
        return new ExceptionHandlerController();
    }
}