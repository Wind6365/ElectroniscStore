package com.example.demo.category;

import com.example.demo.good.Good;
import com.example.demo.good.GoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository repository, GoodRepository goodRepository){
        return args -> {
        };
    }
}
