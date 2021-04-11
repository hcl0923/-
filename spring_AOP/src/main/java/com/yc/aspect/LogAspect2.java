package com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: reflectionAndAnnotation
 * @description:切面类 你要增强的类写在这里
 * @author: 作者
 * @create: 2021-04-09 20:24
 */
@Aspect
@Component//IOC注解  以实现spring托管的功能

public class LogAspect2 {
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")//切入点表达式：那些方法加增强
    private void add() {
    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")//切入点表达式：那些方法加增强
    private void update() {
    }

    @Pointcut("add()||update()")//切入点表达式：那些方法加增强
    private void addAndUpdate() {
        //
    }

    //切入点表达式的语法：？代表出现0次或1次
    //modifiers-pattern:修饰衔
    // ret-type-pattern:返回类型
    // declaring-type-pattern :
    // name-pattern:名字模型
    // throws-pattern
    // execution(modifiers-pattern?ret-type-pattern declaring-type-pattern ?name-pattern(param-pattern)/ /throws-pattern ?)

    //声明前置增强
    @Before("com.yc.aspect.LogAspect2.add()")
    public void log() {
        System.out.println("======前置增强的日志=====");
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dstr = sdf.format(d);
//        System.out.println("执行时间为：" + dstr);
        System.out.println("======前置增强的日志结束=====");
    }

    @Around("com.yc.aspect.LogAspect2.update()")
    public Object log1(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("======前置增强的日志=====");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = sdf.format(d);
        Object o = joinPoint.proceed();
        System.out.println("执行时间为：" + dstr);
        System.out.println("======前置增强的日志结束=====");
        return o;
    }

}








