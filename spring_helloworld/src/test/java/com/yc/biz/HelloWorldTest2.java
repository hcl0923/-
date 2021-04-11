package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DependsOn("helloWorld")
@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration(classes = {AppConfig.class})//加载配置文件
public class HelloWorldTest2 {//测试用例
    
    @Autowired
    private HelloWorld hw;
    @Autowired
    private HelloWorld hw2;

    @Test
    public void testHello() {
        System.out.println("aaa");
        //spring 容器是单例模型
        System.out.println(hw.hashCode() + "\t" + hw2.hashCode());
    }

}