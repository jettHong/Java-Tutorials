package com.jett.java.lang;

public class 阿里_三目运算 {
    public static void main(String[] args) {
        boolean flag = true; // 设置成 true，保证条件表达式的表达式二一定可以执行
        Boolean nullBoolean = null;// 定义一个包装类对象类型的 Boolean 变量，值为 null
        boolean simpleBoolean = false; // 定义一个基本数据类型的 boolean 变量
        boolean x = flag ? nullBoolean : simpleBoolean; // 使用三目运算符并给 x 变量赋值
        System.out.println(x);
    }
}
