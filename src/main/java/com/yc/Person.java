package com.yc;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-03-29 19:00
 */
public class Person implements Showable {
    private String name;
    private int age;

    Person() {
        System.out.println("无参构造方法");
    }

    Person(String s) {
        this.name = name;
        System.out.println("有参构造方法");
    }

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }


}
