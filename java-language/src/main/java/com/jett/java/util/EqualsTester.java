package com.jett.java.util;


import java.util.Objects;

/**
 * == 与 equals 区别
 * 推荐使用 Objects.equals(obj1, obj2) 避免 null
 *
 * @author jett
 */
public class EqualsTester {
    public static void main(String[] args) {
    
        // ------------------------------------------------------
        
        //String 中的 equals 方法是被重写过的，因为 Object 的 equals 方法是比较的对象的内存地址，而 String 的 equals 方法比较的是对象的值。
        //当创建 String 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 String 对象。
        
        // 放在常量池中
        String str1 = "我是中国人";
        // 从常量池中查找
        String str2 = "我是中国人";
        //
        String str3 = "我是中国人";
        
        // true，因为两者存放的都是指向常量池的地址值。
        System.out.println(str1 == str2);
        // false，非同一对象
        System.out.println(str2 == str3);
        
        System.out.println("=============");
        
        // ------------------------------------------------------
    
    
        Integer i01 = 10;
        Integer i02 = new Integer(10);
        // true
        System.out.println(">>>" + Objects.equals(str1, str2));
        // false
        System.out.println(i01 == i02);
        
        
        // 为什么重写 equals 时必须重写 hashCode 方法？
        // 1、hashCode 散列码，有可能冲突。
        // 2、
        // 如果两个对象相等equals，则 hashcode 一定也是相同的。
        // 但是，两个对象有相同的 hashcode 值，它们也不一定是相等的。因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖。
    }
}
