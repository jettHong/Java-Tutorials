package com.jett.java5.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解-只能用于[字段]上面
 * @author jett
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnField {
    
    String name() default "属性默认值";
    
    int order() default -1;
}