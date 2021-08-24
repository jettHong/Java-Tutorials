package com.jett.jvm;

/**
 * 一、
 * 每个Class类，无论创建多少个实例对象，在JVM中都只有一个Class对象，即在内存中每个类有且只有一个相对应的Class对象
 * 这个Class对象的元数据放置于方法区
 */
public class Car {

    public static void main(String[] args) {
        // 类是模板，实例才是具体的
        // 二、实例数据放在堆区 （car1所指的对象放在堆区）
        // 三、引用放在栈区 （car1这个引用放在栈区）
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        System.out.println("---三个实例，hasCode值不一样。----实例.hashCode()");
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());

        System.out.println("---应该是来源于相同类----实例.getClass().hashCode()");
        System.out.println(car1.getClass().hashCode());
        System.out.println(car2.getClass().hashCode());
        System.out.println(car3.getClass().hashCode());

        System.out.println("---三个实例，来源于相同的类加载器 ClassLoader----");
        System.out.println(car1.getClass().getClassLoader().hashCode());
        System.out.println(car2.getClass().getClassLoader().hashCode());
        System.out.println(car3.getClass().getClassLoader().hashCode());

    }
}
