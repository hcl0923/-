package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-05 18:29
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext {
    private Map<String, Object> beanMap = new HashMap<>();
    //private Map<String,Class> classMap=new HashMap<>();

    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(Class<?>[] componentClasses) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException, ClassNotFoundException {
        if (componentClasses == null || componentClasses.length <= 0) {
            throw new RuntimeException("没有指定配置类");
        }
        for (Class cl : componentClasses) {
            //请实现这个里面的代码
            //源码1：只实现IOC，MyPostConstruct MyPreDestroy
            if (!cl.isAnnotationPresent(MyConfiguration.class)) {
                continue;
            }
            String[] basePackages = getAppConfigBasePackages(cl);
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan mcs = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (mcs.basePackages() != null && mcs.basePackages().length > 0) {
                    basePackages = mcs.basePackages();
                }
            }
            //处理@MyBean的情况
            Object obj = cl.newInstance();
            handleAtMyBean(cl, obj);
            //处理 basePackages基础包下的所有托管bean
            for (String basePackage : basePackages) {
                scanPackageAndSubPackageClasses(basePackage);
            }
            //继续处理其他托管bean
            handleManagedBean();
            //版本2：循环beanMap中的每个bean，找到他们每个类由@Autowired  @Repository的注解的方法以实现di
            handleDi(beanMap);
        }
    }

    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection = beanMap.values();
        for (Object obj : objectCollection) {
            Class cls = obj.getClass();
            Method[] ms = cls.getDeclaredMethods();
            for (Method m : ms) {
                if (m.isAnnotationPresent(MyAutowired.class) && m.getName().startsWith("set")) {
                    invokeAutowiredMethod(m, obj);
                } else if (m.isAnnotationPresent(MyRepository.class) && m.getName().startsWith("set")) {
                    invokeResourceMethod(m, obj);
                }
            }
            Field[] fs = cls.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(MyAutowired.class)) {

                } else if (field.isAnnotationPresent(MyRepository.class)) {

                }
            }
        }

    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出MyResource中的name属性值
        MyResource mr = m.getAnnotation(MyResource.class);
        String beanId = mr.name();
        //2.如果没有，则取出m方法中参数的类型名，改成首字母小写  当成beanId
        if (beanId == null || beanId.equalsIgnoreCase("")) {
            String pname = m.getParameterTypes()[0].getSimpleName();
            beanId = pname.substring(0, 1).toLowerCase() + pname.substring(1);
        }
        //3.从beanMap中取出
        Object o = beanMap.get(beanId);
        //4.invoke
        m.invoke(obj, o);
    }

    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出m的参数的类型
        Class typeClass = m.getParameterTypes()[0];
        //2.从beanMap中循环所有的object，
        Set<String> keys = beanMap.keySet();

        for (String key : keys) {
            //4.如果是，则从beanMap取出。
            Object o = beanMap.get(key);
            //3.判断这些object是否为  参数类型的实例 instanceof
            Class[] interfaces = o.getClass().getInterfaces();
            for (Class c : interfaces) {
                System.out.println(c.getName() + "\t" + typeClass);
                if (c == typeClass) {
                    //5.invoke
                    m.invoke(obj, o);
                    break;
                }
                m.invoke(obj, o);
            }
        }
    }

    private void saveManagedBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = c.newInstance();
        handlePostConstruct(o, c);
        String beanId = c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
        beanMap.put(beanId, o);
    }

    /**
     * 处理managedBeanClasses所有的class类，筛选出所有的@Component @Service，@Repository，的类，并实例化，存到beanMap中
     */
    private void handleManagedBean() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Class c : managedBeanClasses) {
            if (c.isAnnotationPresent(MyComponent.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyService.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyRepository.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyController.class)) {
                saveManagedBean(c);
            } else {
                continue;
            }
        }
    }

    private void scanPackageAndSubPackageClasses(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath = basePackage.replaceAll("\\.", "/");
        System.out.println("扫描包的路径：" + basePackage + ",替换后：" + packagePath);//com.yc.bean相对路径
        Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);//绝对路径
        while (files.hasMoreElements()) {
            URL url = files.nextElement();
            System.out.println("配置的扫描路径为：" + url.getFile());
            //递归这些目录，查找class文件
            findClassesInPackages(url.getFile(), basePackage);
            //第二个参数：com.yc.bean
        }
    }

    private Set<Class> managedBeanClasses = new HashSet<Class>();

    /**
     * 查找  file 下面及子包所有的要托管的class，存到一个set中，
     *
     * @param file
     * @param basePackage
     */
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f = new File(file);
        File[] classFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class") || file.isDirectory();
            }
        });
//        System.out.println(classFiles);
        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                //如果是目录，则递归
                //拼接子目录
                basePackage += "." + cf.getName().substring(cf.getName().lastIndexOf("/") + 1);
                findClassesInPackages(cf.getAbsolutePath(), basePackage);
            } else {
                //加载   cf  作为 class文件
                URL[] urls = new URL[]{};
                URLClassLoader ucl = new URLClassLoader(urls);
                Class c = ucl.loadClass(basePackage + "." + cf.getName().replace(".class", ""));//com.yc.bean.Hello.class
                managedBeanClasses.add(c);
            }
        }
    }

    /**
     * 获取当前  AppConfig所在类的包路径
     *
     * @param cl
     * @return
     */
    private String[] getAppConfigBasePackages(Class cl) {
        String[] paths = new String[1];
        paths[0] = cl.getPackage().getName();
        return paths;
    }

    /**
     * @param cls
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handleAtMyBean(Class cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.选取cls中所有的method
        Method[] ms = cls.getDeclaredMethods();
        //2.循环，判断  每个method上是否有@MyBean注解
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyBean.class)) {
                //3.有，则invoke它，它有返回值，将空上返回值存到beanMap,键是方法名，值是返回值对象
                Object o = m.invoke(obj);
                //加入处理  @MyBean的注解对应的方法所实例化的类中的@MyPostConstruct对应的方法
                handlePostConstruct(o, o.getClass());//o在这里值HelloWorld对象 o.getClass()它反射对象
                beanMap.put(m.getName(), o);
            }
        }
    }

    /**
     * 处理一个Bean中的  @MyPostConstruct对应的方法
     *
     * @param o
     * @param cls
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyPostConstruct.class)) {
                m.invoke(o);
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}