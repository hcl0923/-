package com.yc.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 19:21
 */
@Component
public class HelloWorld {
    @PostConstruct
    public void setup() {
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void destory() {
        System.out.println("PreDestory");
    }

    public void show() {
        System.out.println("show");
    }

    public HelloWorld() {
        System.out.println("Hello World构造");
    }
}
