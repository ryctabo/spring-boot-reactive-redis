package com.ryctabo.spring.reactive.redis;

import com.ryctabo.spring.reactive.redis.service.CoffeeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@AllArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final CoffeeService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Console Application running!");
        log.info("Print all coffees");
        service.getCoffees().forEach(log::info);
    }
}
