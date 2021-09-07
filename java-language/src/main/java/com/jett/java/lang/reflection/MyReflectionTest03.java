package com.jett.java.lang.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射-性能比对
 * 1、普通调用性能赛高
 * 2、invoke调用，性能下降，设置 setAccessible(true) 能提高点性能。
 */
public class MyReflectionTest03 {
    public static void main(String[] args) throws Exception {
        System.out.println(Integer.MAX_VALUE);
        normal();
        invoke(false);
        invoke(true);
//        normal：62
//        invoke(false)：5485
//        invoke(true)：2431
    }
    
    public static void normal() {
        UserInfo userInfo = new UserInfo();
        long beg = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            userInfo.setAge(1);
        }
        long end = System.currentTimeMillis();
        System.out.println("normal：" + (end - beg));
    }
    
    public static void invoke(boolean accessible) throws Exception {
        UserInfo userInfo = new UserInfo();
        long beg = System.currentTimeMillis();
        Method setAge = userInfo.getClass().getDeclaredMethod("setAge", Integer.TYPE);
        if (accessible) {
            setAge.setAccessible(accessible);
        }
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            setAge.invoke(userInfo, 1);
        }
        long end = System.currentTimeMillis();
        System.out.println("invoke(" + accessible + ")：" + (end - beg));
    }
}
