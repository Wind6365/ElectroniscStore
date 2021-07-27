package com.example.demo.good;

import com.example.demo.category.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodConfig {
    @Bean
    CommandLineRunner commandLineRunner(GoodRepository goodRepository, CategoryRepository categoryRepository){
        return args -> {
            /*Good computer = new Good("computer");
            Good mobile = new Good("mobile");
            repository.saveAll(List.of(computer, mobile));*/
        };
    }
}
