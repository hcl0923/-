package com.huwei;

import com.AppConfig;
import com.huwei.bean.Container;
import com.mimi.bean.Person;
import com.mimi.bean.PersonBmiTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 10:09
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration(classes = {AppConfig.class})//加载配置文件
public class ContainerTest2 implements ApplicationContextAware, BeanNameAware {
    @Autowired
    private Container c;
    @Autowired
    private Random r;
    @Autowired
    private PersonBmiTool pbt;


    @Test
    public void testSave() {
        System.out.println(ac.getEnvironment().toString());

        System.out.println(bn.toString());
        Person p1 = new Person("张三", 1.70, 80);
        Person p2 = new Person("李四", 1.70, 80);
        Person p3 = new Person("王五", 1.70, 80);
        Person p4 = new Person("赵六", 1.70, 80);
        Person p5 = new Person("田七", 1.70, 80);
        Person p6 = new Person("王八", 1.70, 80);
        c.save(p1);
        c.save(p2);
        c.save(p3);
        c.save(p4);
        c.save(p5);
        c.save(p6);

        for (int i = 0; i < 1000; i++) {
            //  Math.random()   生成 0-1之间的小数
            Person p7 = new Person("王八" + i, 1 + Math.random(), r.nextInt(80) + 30);
            c.save(p7);
        }


        Person max = (Person) c.getMax();   //取最大值
        Person min = (Person) c.getMin();   //最最小值

        System.out.println("最大值:" + max.getName());
        System.out.println("最小值:" + min.getName());

        System.out.println("平均bmi:" + c.getAvg());

        Object[] objs = c.getObjs();
        for (Object o : objs) {
            Person pp = (Person) o;
            System.out.println(pp.getName() + "\t" + pp.getHeight() + "\t" + pp.getWeight() + "\t" + pbt.measure(pp));
        }

    }

    private ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    String bn;

    @Override
    public void setBeanName(String s) {
        bn = s;
    }
}
