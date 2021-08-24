package com.jett.java.other;

import java.util.Arrays;
import java.util.Random;

/**
 * 舒尔特方格训练
 * 在一张方形卡精上画上1cm*1cm 的25个方格，格子内任意
 * 填写上阿拉伯数字1~ 25 等共25个数字。训练时，要求
 * 孩子用手指按1 ~ 25的顺序依次指出其位置，同时诵读出
 * 声，家长一旁记录所用时间。数完25个数字所用时间越短，
 * 注意力水平越高。
 */
public class SchulteGrid {
    public static void main(String[] args) {
        // 生成刚好 5 个表格，向下排列。
        gen();
        System.out.println();
        gen();
        System.out.println();
        gen();
        System.out.println();
        gen();
        System.out.println();
        gen();
    }
    
    /**
     * 生成 5*5 数据，以 tab 键隔开。
     */
    private static void gen() {
        Integer[] src = new Integer[25];
        for (int i = 0; i < 25; i++) {
            src[i] = i + 1;
        }
        // 按时间作种子
        Random rd = new Random(System.currentTimeMillis());
        Integer time = 0;
        Integer temp = 0;
        while (time++ < 100) {
            int i = rd.nextInt(25);
            int j = rd.nextInt(25);
            temp = src[i];
            src[i] = src[j];
            src[j] = temp;
        }
        // 逢5换行（这里下标从1开始方便计算）
        for (int i = 1; i < 26; i++) {
            System.out.print(src[i - 1] + "\t");
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }
}
