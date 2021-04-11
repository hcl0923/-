package com.yc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: reflectionAndAnnotation
 * @description:在程序中 运行中，有人给了一个类，请动态的了解这个类，并创建这个类的对象
 * @author: 作者
 * @create: 2021-03-29 19:00
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入类路径：");
            String path = sc.nextLine();
            System.out.println("待加载的类为：" + path);

            Class c = Class.forName(path);
            String name = c.getName();//Declared 自己声明的
            System.out.println(name);

            //Field[] fs=c.getFields();
            Field[] fs = c.getDeclaredFields();
            if (fs != null && fs.length > 0) {
                for (Field f : fs) {
                    //private
                    String modifier = "";
                    switch (f.getModifiers()) {
                        case 1:
                            modifier = "public";
                            break;
                        case 2:
                            modifier = "private";
                            break;
                    }
                    System.out.println(modifier + "\t" + f.getName());
                    //算法：位图算法
                }
            }
            Method[] ms = c.getDeclaredMethods();
            //Method[] ms=c.getMethods();
            if (ms != null && ms.length > 0) {
                for (Method m : ms) {
                    System.out.println(m.getModifiers() + "\t" + m.getReturnType().toString() + "\t" + m.getName());
                }
            }
            Constructor[] cs = c.getConstructors();
            if (cs != null && cs.length > 0) {
                for (Constructor m : cs) {
                    System.out.println(m.getModifiers() + "\t" + m.getName());
                }
            }
            //利用反射完成实例化操作
            Object o = c.newInstance();//无参构造方法
            //这个是已知接口名。。。instanceof
            if (o instanceof Showable) {
                Showable p = (Showable) o;
                p.show();
            }
            //利用反射调用某个方法  适合j2ee中的规范化方法调用场景：setXxx(); setXXX();
            System.out.println("=========================");
            if (ms != null && ms.length > 0) {
                for (Method m : ms) {
                    if (m.getName().startsWith("sh")) {
                        //调用此方法 show()
                        m.invoke(o);
                    }
                }
            }
            Map<String, String> pMap = new HashMap<String, String>();
            pMap.put("name", "张三");
            pMap.put("age", 30 + "");
            Object oo = setValues(pMap, c);
            System.out.println(oo.toString());

        }

    }

    /**
     * 反射功能模块：将Map中保存的 属性值存到 cls对应的对象中。注意已知cls满足j2ee的javaBean规范（setXxx getXxx）;
     *
     * @param pMap
     * @param cls
     * @return
     */
    private static Object setValues(Map<String, String> pMap, Class cls) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o = null;
        o = cls.newInstance();
        Method[] ms = cls.getDeclaredMethods();
        if (ms != null && ms.length > 0) {
            for (Method m : ms) {
                //只有setXxx才能激活
                if (m.getName().startsWith("set")) {
                    String mName = m.getName();
                    String fName = mName.substring(3).toLowerCase();
                    String value = pMap.get(fName);
                    if ("Integer".equalsIgnoreCase(m.getParameterTypes()[0].getName()) || "Int".equalsIgnoreCase(m.getParameterTypes()[0].getName())) {
                        m.invoke(o, Integer.parseInt(value));
                    } else {
                        m.invoke(o, value);
                    }
                }

            }
        }
        return o;
    }
}
