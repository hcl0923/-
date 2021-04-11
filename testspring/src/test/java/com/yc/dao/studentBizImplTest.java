package com.yc.dao;

import com.yc.AppConfig;
import com.yc.biz.StudentBizImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: reflectionAndAnnotation
 * @description:因为这个类继承自TestCase，所以这个类为测试用例类,这个类中所有以test开头的方法都成为测试方法 就不用加 @Test
 * @author: 作者
 * @create: 2021-04-05 09:02
 */
public class studentBizImplTest {
    //容器
    ApplicationContext ac;
    private StudentBizImpl studentBiz;

    @Before
    public void setup() {
        //java.lang.String
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        studentBiz = (StudentBizImpl) ac.getBean("studentBizImpl");
    }

    @Test
    public void testAdd() {

        studentBiz.add("李四");
    }

    @Test
    public void testUpdate() {
        studentBiz.update("李四");
    }

}
