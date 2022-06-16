package com.example.spring_shop.item;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {

        };
    };
}
