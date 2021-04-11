package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 19:52
 */
public class Test {
    public static void main(String[] args) {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        HelloWorld hw = (HelloWorld) ac.getBean("hw");
        hw.show();
    }
}
