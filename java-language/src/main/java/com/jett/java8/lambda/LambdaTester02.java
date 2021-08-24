package com.jett.java8.lambda;

/**
 * Lambda 表达式
 * 演示加减乘除四种运算使用不同写法。（回字几种写法 -_-b）
 */
public class LambdaTester02 {

    public static void main(String[] args) {

        LambdaTester02 tester = new LambdaTester02();

        // 有类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
    }

    /**
     * 操作方法
     * @param a 参数1
     * @param b 参数2
     * @param mathOperation 操作符
     * @return
     */
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    /**
     * 数学操作符接口
     */
    interface MathOperation {
        int operation(int a, int b);
    }

}
