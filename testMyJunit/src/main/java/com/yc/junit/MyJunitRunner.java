package com.yc.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-03-31 19:59
 */
public class MyJunitRunner {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //因为没有idea的插件，只能先做 class加载
        Class cls = Class.forName("com.yc.MyCalculatorTest");
        //TODO:升级：。。。按照maven的约定的目录要求扫描  test/java下的单元测试类
        //1.获取这个类中所有方法
        Method[] ms = cls.getDeclaredMethods();
        List<Method> testMethods = new ArrayList<Method>();
        Method beforeMethod = null;
        Method afterMethod = null;
        Method beforeClassMethod = null;
        Method afterClassMethod = null;

        //2.循环这些方法，判断上面加了哪个注解
        for (Method m : ms) {
            //3.将这些方法存好,@Test对应的方法有很多，存到一个集合中，其他注解对应的方法只有一个，直接存
            if (m.isAnnotationPresent(MyTest.class)) {
                testMethods.add(m);//添加到集合中去
            }
            if (m.isAnnotationPresent(MyBefore.class)) {
                beforeMethod = m;
            }
            if (m.isAnnotationPresent(MyAfter.class)) {
                afterMethod = m;
            }
            if (m.isAnnotationPresent(MyBeforeClass.class)) {
                beforeClassMethod = m;
            }
            if (m.isAnnotationPresent(MyAfterClass.class)) {
                afterClassMethod = m;
            }
        }

        //4.按junit的运行的生命周期来调用
        /*
        beforeClass
        before
        add测试
        after
        before
        sub测试
        after
        AfterClass
         */
        if (testMethods == null || testMethods.size() <= 0) {
            throw new RuntimeException("没有要测试的方法");
        }
        Object o = cls.newInstance();//实例化  测试类
        beforeClassMethod.invoke(o, null);  // @BeforeClass
        for (Method m : testMethods) {
            if (beforeMethod != null) {
                beforeMethod.invoke(o, null);//@Before
            }
            m.invoke(o, null);//测试方法
            if (afterMethod != null) {
                afterMethod.invoke(o, null);//@After
            }
        }
        afterClassMethod.invoke(o, null);//@AfterClass
    }
}
