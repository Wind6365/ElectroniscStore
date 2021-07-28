package com.example.demo.category;

import com.example.demo.good.Good;
import com.example.demo.good.GoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository repository, GoodRepository goodRepository){
        return args -> {
        };
    }
}
