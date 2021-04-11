package com.yc.bean;

import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestory;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 19:21
 */
//@MyComponent
public class HelloWorld {
    @MyPostConstruct
    public void setup() {
        System.out.println("postConstruct");
    }

    @MyPreDestory
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
