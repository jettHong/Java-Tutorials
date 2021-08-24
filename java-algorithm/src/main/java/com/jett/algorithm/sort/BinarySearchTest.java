package com.jett.algorithm.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 二分查找也称折半查找（Binary Search）
 * 输入的线性表必需是有顺序的。
 *
 * @author jett
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        Integer[] input = {0, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        // 不包含10
        int key = random.nextInt(10);
        int i = 5;
        while (i-- > 0) {
            System.out.println("请输入答案：");
            String answer = sc.nextLine();
            if (Integer.valueOf(answer) == key) {
                System.out.println("恭喜你，答对了，答案是：" + key);
                return;
            } else {
                if (Integer.valueOf(answer) < key) {
                    System.out.println("请往大一点猜");
                } else {
                    System.out.println("请往小一点猜");
                }
            }
        }
        System.out.println("你猜不到，答案是：" + key);
    }
}
