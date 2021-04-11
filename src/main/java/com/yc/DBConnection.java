package com.yc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DBConnection {
    //联接数据库的属性
    public String url() default "";

    public String driverClass();

    public String user() default "root";

    public String password() default "232328";
}
