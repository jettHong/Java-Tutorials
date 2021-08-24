package com.jett.java.lang.reflection;

/**
 * 反射-获取类对象
 */
public class MyReflectionTest01 {
    public static void main(String[] args) {
        // 1、根据实例取得
        MyReflectionTest01 t01 = new MyReflectionTest01();
        Class<? extends MyReflectionTest01> c01 = t01.getClass();
    
        // 2、通过Class.forName("全限定类名");
        Class c02 = null;
        try {
            c02 = Class.forName("com.jett.java.lang.reflection.MyReflectionTest01");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 3、直接通过全限定类名取得
        Class<? extends MyReflectionTest01> c03 = MyReflectionTest01.class;
    
        System.out.println(c01.hashCode());
        System.out.println(c02.hashCode());
        System.out.println(c03.hashCode());
    
        // 4、基本类型可以通过Type属性取得
        Class ci = Integer.TYPE;
        Class cl = Long.TYPE;
    }
}
