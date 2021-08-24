package com.jett.java5.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解-只能用于[方法]上面
 *
 * @author jett
 */
@Target(ElementType.METHOD) //表示这个注解只能用于方法
@Retention(RetentionPolicy.RUNTIME) //表示这个注解作用于在"运行时"范围（最大）
@interface MyAnnMethod {
    String value();
}
    