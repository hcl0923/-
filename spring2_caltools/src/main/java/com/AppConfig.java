package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 09:12
 */
@Configuration
@ComponentScan(basePackages = {"com.huwei", "com.mimi"})
public class AppConfig {

    @Bean//beanid :r
    public Random r() {
        return new Random();
    }
}
