package com.yc.biz;

import com.yc.MyAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyAppConfig.class)

public class StudentBizImplTest {
    @Autowired
    @Qualifier(value = "studentBizImpl")
    private biz sbi;
    @Autowired
    private ApplicationContext ac;

    @Test
    public void testAdd() {
        Object o = ac.getBean("studentBizImpl");
        sbi.add("张三");
        System.out.println(ac.getBean("studentBizImpl"));
    }

    @Test
    public void testUpdate() {
        sbi.update("张三");
    }

    @Test
    public void testFind() throws InterruptedException {
        sbi.find("张三");
    }
}