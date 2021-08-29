package com.jett.java5.box;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BoxMain {
    public static void main(String[] args) {
        //装箱就是自动将基本数据类型转换为包装器类型；拆箱就是自动将包装器类型转换为基本数据类型。
        
        //自动装箱
        Integer total = 99;
        
        //自动拆箱
        int totalPrim = total;
        
        // ============= 坑 ================
        // 例子01
        boolean flag = true;
        Integer i = null;
        int j = 1;
        // 下列行会报错
        // 三目运算符的语法规范：当第二，第三位操作数分别为基本类型和对象时，其中的对象就会拆箱为基本类型进行操作
        int k = flag ? i : j;
        
        // 例子01
        Map<String, Boolean> map = new HashMap<>(2);
        Boolean b = map != null ? map.get("不存在的KYE") : false;
        System.out.println(b);
    }
    
    /**
     * int 与 Integer 比对
     * https://www.fangzhipeng.com/javainterview/2019/02/25/int-integer.html
     */
    @Test
    public void intBox() {
        {
            Integer i = new Integer(100);
            Integer j = new Integer(100);
            // 因为变量实际上是对一个Integer对象的引用，两个对象其内存地址不同。
            System.out.println(i == j); // false
        }
        {
            Integer i = new Integer(100);
            int j = 100;
            // 因为 i 自动拆箱成为 int 类型。
            System.out.println(i == j); // true
        }
        {
            Integer i = new Integer(100);
            Integer j = 100;
            Integer k = 100;
            System.out.println(i == j); // false
            System.out.println(j == k); // true
            // 因为非new生成的Integer变量指向的是java常量池中的对象，
            // Integer j = 100; ==编译成==>   Integer j = Integer.valueOf(100)
            // java对于-128到127之间的数，会进行缓存。
            // 详细见：java.lang.Integer.valueOf(int)
            //         if (i >= IntegerCache.low && i <= IntegerCache.high)
            //            return IntegerCache.cache[i + (-IntegerCache.low)];
            // @see: java.lang.Integer.IntegerCache
        }
    }
}
