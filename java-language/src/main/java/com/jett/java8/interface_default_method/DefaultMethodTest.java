package com.jett.java8.interface_default_method;

import java.util.Arrays;
import java.util.List;

/**
 * java 8 之后接口的默认方法示例，Iterable.forEach
 * @author jett
 */
public class DefaultMethodTest {
    
    public static void main(String[] args) {
        String[] array = new String[]{
                "hello",
                ", ",
                "world",
        };
        List<String> list = Arrays.asList(array);
        
        // 这是 jdk 1.8 新增的接口默认方法
        list.forEach(System.out::println);
    }
}
