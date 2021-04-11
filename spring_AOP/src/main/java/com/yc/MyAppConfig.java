package com.yc;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 19:55
 */
@Configuration
@ComponentScan(basePackages = {"com.yc.*"})
@EnableAspectJAutoProxy//启用aspect支持
public class MyAppConfig {

//    @MyBean
//    public HelloWorld hw() {
//        return new HelloWorld();//method.invoke(MyAppConfig对象，xxx)
//    }
//
//    @MyBean
//    public HelloWorld hw2() {//method.invoke(MyAppConfig对象，xxx)
//        return new HelloWorld();
//    }
}
