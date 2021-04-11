package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-04 14:52
 */
@Service
public class StudentBizImpl implements biz {
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {

    }

    //    @Inject
    @Autowired
    @Qualifier("studentDaoJpalImpl")
//    @Qualifier("studentDaoJpalImpl")
//    @Resource
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int add(String name) {
        System.out.println("==============业务层=======");
        System.out.println("用户名是否重名");
        int result = studentDao.add(name);
        System.out.println("============业务操作结束=======");
        return result;
    }

    @Override
    public void update(String name) {
        System.out.println("============业务层=======");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("============业务操作结束=======");
    }

    @Override
    public String find(String name) throws InterruptedException {
        System.out.println("============业务层=======");
        System.out.println("业务层查找用户名：" + name);
        studentDao.find(name);
        Random r = new Random();
        Thread.sleep(r.nextInt(10));
        System.out.println("============业务操作结束=======");
        return name;
    }
}
