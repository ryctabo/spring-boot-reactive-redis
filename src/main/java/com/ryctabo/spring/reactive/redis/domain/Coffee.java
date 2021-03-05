package com.ryctabo.spring.reactive.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    private String id;

    private String name;
}
