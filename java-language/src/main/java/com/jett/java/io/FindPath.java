package com.jett.java.io;

/**
 * 文件查找，路径
 */
public class FindPath {
    public static void main(String[] args) {
        System.out.println(FindPath.class.getClassLoader().getResource("").getPath());
        System.out.println(FindPath.class.getClassLoader().getResource(".").getPath());
        System.out.println(FindPath.class.getClassLoader().getResource("gbk.txt").getPath());
    }
}
