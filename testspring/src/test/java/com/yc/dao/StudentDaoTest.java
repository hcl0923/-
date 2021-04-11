package com.yc.dao;

import com.yc.biz.StudentBizImpl;
import junit.framework.TestCase;

public class StudentDaoTest extends TestCase {
    private StudentDao studentDao;
    private StudentBizImpl studentBizImpl;

    @Override
    public void setUp() throws Exception {
        //1.能否自动完成  实例化对象  -》IOC  控制 反转-》有容器实例化对象，有容器来完成
        studentDao = new StudentDaoJpalImpl();

        //studentImpl=new StudentBizImpl(studentDao);//IOC

        studentBizImpl = new StudentBizImpl();
        //2.能否自动完成  装载过程  ->DI  依赖注入  ->有容器装配对象
        studentBizImpl.setStudentDao(studentDao);
    }

    public void testAdd() {
        studentDao.add("张三");
    }

    public void testUpdate() {
        studentDao.update("张三");
    }

    public void testBizAdd() {
        studentBizImpl.add("张三");
    }
}