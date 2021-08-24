package com.jett.java8.functional_interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate是一个函数式接口，可以被应用于lambda表达式和方法引用。
 */
public class PredicateTest {
    
    public static void main(String[] args) {
        
        // 示例1，原始写法
        // 使用 test 函数进行断言，返回true或者false。
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return "张三".equals(s);
            }
        };
        
        System.out.println(predicate.test("李四"));
        System.out.println(predicate.test("张三"));
        
        // 示例2，简化写法
        System.out.println("判断传入参数是否大于 7");
        Predicate<Integer> pdNumber = n -> n > 7; // 声明（谓语）条件
        System.out.println(pdNumber.test(10)); // 输出 true
        System.out.println(pdNumber.test(11)); // 输出 true
        System.out.println(pdNumber.test(5));  // 输出 false
        
        System.out.println("再补充必需是偶数条件");
        pdNumber = pdNumber.and(n -> n % 2 == 0);
        System.out.println(pdNumber.test(10)); // 输出 true
        System.out.println(pdNumber.test(11)); // 输出 false
        System.out.println(pdNumber.test(5));  // 输出 false
        
        
        // 示例3，函数式接口 -> 将函数当成参数传递
        System.out.println("Evaluates =====");
        List<Integer> list = Arrays.asList(6, 7, 8, 9, 10);
        eval(list, n -> true);       // 恒返回真
        eval(list, n -> n > 7);      // 大于 7
        eval(list, n -> n % 2 == 0); // 是否偶数
        
    }
    
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }
}