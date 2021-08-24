package com.jett.java5.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解-只能用于[类]上面
 *
 * @author jett
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnType {
    
    String name() default "默认值";
    
    int time() default 0;
}