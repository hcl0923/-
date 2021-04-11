package com.yc.biz;

public interface biz {
    int add(String name);

    void update(String name);

    String find(String name) throws InterruptedException;
}
