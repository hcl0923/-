package com.yc.biz;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-10 19:20
 */
public class StudentBizImpl implements StudentBiz {

    @Override
    public int add(String name) {
        System.out.println("調用了studentBizImpl中的add" + name);
        return 100;
    }

    @Override
    public void update(String name) {
        System.out.println("");
    }

    @Override
    public String String(String name) {
        return null;
    }
}
