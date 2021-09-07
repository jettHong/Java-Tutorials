package com.jett.java.lang.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射-创建实例
 * 1.获取构造器对象方法：
 * 1).批量的方法：
 * public Constructor[] getConstructors()：所有"公有的"构造方法
 * public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
 * 2).获取单个的方法:
 * public Constructor getConstructor(Class… parameterTypes): 获取单个的"公有的"构造方法
 * public Constructor getDeclaredConstructor(Class…parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 */
public class MyReflectionTest02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 取得对象实例
        // 1.获取类对象
        Class clazz = Class.forName("com.jett.java.lang.reflection.UserInfo");
        // 2、获取构造器对象
        // Constructor con = clazz.getConstructor(形参.class);
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("构造函数：" + constructor);
            
        }
        System.out.println("--------------------");
        
        // 取得默认无参构造函数
        Constructor con = clazz.getConstructor();
        // 3、获取对象
        // con.newInstance(实参);
        Object obj = con.newInstance();
        System.out.println(obj);
        System.out.println("--------------------");
        
        // ==============成员方法 getMethod=================
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "，args：" + method.getParameterTypes() + "，returnType" + method.getReturnType());
        }
        
    }
}
