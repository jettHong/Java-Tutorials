package com.jett.java5;

import static java.nio.charset.StandardCharsets.UTF_8;
// import static java.nio.charset.StandardCharsets.UTF_8; // 这里静态导入常量，

/**
 * 静态导入，不常用
 * 优点：不用写类全称
 * 缺点：过度使用，容易混淆
 */
public class StaticImport {
    public static void main(String[] args) {
        System.out.println(UTF_8);
    }
}
