package com.jett.java8.lambda;

import java.util.function.Consumer;

/**
 * Lambda 表达式
 * java内建方法的使用
 * https://zhuanlan.zhihu.com/p/28093333
 */
public class LambdaTester03 {
    
    public static void donation(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }
    
    public static void main(String[] args) {
        donation(1000, money -> System.out.println("好心的嘎子为潘子捐赠了" + money + "元"));
    }
    
    
}
