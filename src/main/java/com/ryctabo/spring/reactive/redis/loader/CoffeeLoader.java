package com.ryctabo.spring.reactive.redis.loader;

import com.ryctabo.spring.reactive.redis.domain.Coffee;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Log4j2
@Component
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CoffeeLoader {

    private final ReactiveRedisConnectionFactory factory;

    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    @PostConstruct
    public void setUp() {
        log.info("Creating all coffees");
        factory.getReactiveConnection()
                .serverCommands()
                .flushAll()
                .thenMany(Flux
                        .just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                        .map(this::buildCoffee)
                        .flatMap(this::saveCoffee))
                .subscribe(log::info);
    }

    private Coffee buildCoffee(String name) {
        return new Coffee(UUID.randomUUID().toString(), name);
    }

    private Mono<Boolean> saveCoffee(Coffee coffee) {
        return coffeeOps.opsForValue().set(coffee.getId(), coffee);
    }
}
