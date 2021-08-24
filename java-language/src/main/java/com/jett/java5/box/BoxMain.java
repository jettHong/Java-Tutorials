package com.jett.java5.box;

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
}
