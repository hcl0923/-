package com.yc.dao;

import java.util.Random;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-04 14:49
 */
//@Repository
public class StudentDaoMybatisImpl implements StudentDao {
    @Override
    public int add(String name) {
        System.out.println("mybatis 添加学生：" + name);
        Random r = new Random();
        return r.nextInt();
    }

    @Override
    public void update(String name) {
        System.out.println("mybatis更新学生：" + name);
    }

    @Override
    public String find(String name) {
        System.out.println("Mybatis查找：" + name);
        return name;
    }
}
