package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 09:12
 */
@Configuration//表示当前的类是一个配置类
@ComponentScan(basePackages = "com.yc")//将来要托管的Bean要扫描的包及子包
public class AppConfig {//java容器的配置
}
