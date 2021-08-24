package com.jett.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序在双层循环中实现，其中外层循环控制排序轮数，总循环次数为要排序数组的长度减 1。
 * 而内层循环主要用于对比相邻元素的大小，以确定是否交换位置，对比和交换次数依排序轮数而减少。
 *
 * @author jett
 */
public class BubbleSortTest {
    public static void main(String[] args) {
        int[] in = {6, 5, 4, 7, 9, 2, 3, 0, 1};
        
        for (int i = 0; i < in.length; i++) {
            for (int j = i + 1; j < in.length; j++) {
                if (in[i] > in[j]) {
                    swap(in, i, j);
                }
            }
        }
        System.out.println(Arrays.toString(in));
    }
    
    /**
     * 交换两者位置
     *
     * @param obj
     * @param i
     * @param j
     */
    public static void swap(int[] obj, int i, int j) {
        int temp = obj[i];
        obj[i] = obj[j];
        obj[j] = temp;
    }
}
