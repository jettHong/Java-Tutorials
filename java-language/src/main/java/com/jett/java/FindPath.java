package com.jett.java;

/**
 * 文件查找
 */
public class FindPath {
    public static void main(String[] args) {
        System.out.println(FindPath.class.getClassLoader().getResource("").getPath());
        System.out.println(FindPath.class.getClassLoader().getResource(".").getPath());
        System.out.println(FindPath.class.getClassLoader().getResource("gbk.txt").getPath());
    }
}
