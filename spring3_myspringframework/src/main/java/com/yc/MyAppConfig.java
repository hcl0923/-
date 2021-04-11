package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 19:55
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.dao", "com.yc.biz"})
public class MyAppConfig {

    @MyBean
    public HelloWorld hw() {
        return new HelloWorld();//method.invoke(MyAppConfig对象，xxx)
    }
//
//    @MyBean
//    public HelloWorld hw2() {//method.invoke(MyAppConfig对象，xxx)
//        return new HelloWorld();
//    }
}
