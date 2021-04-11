package com.yc;

//要声明他的特征。  Target：表示这个注解可以加在类的那个地方
//                Retention：表示这个注解在什么时候还保留（源码，字节码，运行）
//@Target叫元注解，即注解的注解，用来描述一个注解的约束
//value={} 这表示Target注解的属性 value赋值了一个属猪的值

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented         //生成的 javadoc文档中还保留这个注解
@Inherited     //子类是否可以继承   父类的注解public @interface MyHelloWorld {
public @interface MyHelloWorld {
    
}
