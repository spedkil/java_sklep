package com.example.spring_shop.item;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository repository){
        return args -> {
            Item milk = new Item(
                    "milk",
                    3.99f
            );

            Item bread = new Item(
                    "bread",
                    2.99f
            );

            Item pasta = new Item(
                    "pasta",
                    5.99f
            );

            repository.saveAll(
                    List.of(milk, bread, pasta)
            );
        };
    };
}
