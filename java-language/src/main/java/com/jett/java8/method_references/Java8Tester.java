package com.jett.java8.method_references;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Java8Tester {
    public static void main(String[] args) {
        List<String> names = new ArrayList();
        
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        
        // 方法引用使用一对冒号 ::
        names.forEach(System.out::println);
    }
}
