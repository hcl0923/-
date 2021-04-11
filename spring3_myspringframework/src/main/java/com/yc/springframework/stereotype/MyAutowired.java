package com.yc.springframework.stereotype;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
}
