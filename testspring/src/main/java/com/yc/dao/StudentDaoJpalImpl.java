package com.yc.dao;

import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-04 14:48
 */
@Repository//异常转化：从Exception转为RuntimeException
public class StudentDaoJpalImpl implements StudentDao {
    @Override
    public int add(String name) {
        System.out.println("jpa 添加学生：" + name);
        Random r = new Random();
        return r.nextInt();
    }

    @Override
    public void update(String name) {
        System.out.println("jpa更新学生：" + name);
    }
}
