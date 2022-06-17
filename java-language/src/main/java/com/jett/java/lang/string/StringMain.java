package com.jett.java.lang.string;

/**
 * String intern 的作用
 */
public class StringMain {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("1");
        s1.intern();                  // intern()作用: 主动将字符串自身的引用添加到字符串常量池,如果字符串常量池中已存在,则直接返回已存在的引用,不存在则添加。
        String s2 = "11";
        System.out.println(s1 == s2); // true：
        
        String s3 = new String("11");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); // false：
        
        System.out.println(s2 == s4); // true：
    }
}
