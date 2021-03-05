package com.ryctabo.spring.reactive.redis.service;

import com.ryctabo.spring.reactive.redis.domain.Coffee;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class CoffeeService {

    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    public Collection<Coffee> getCoffees() {
        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get)
                .as(flux -> flux.collectList().block());
    }
}
