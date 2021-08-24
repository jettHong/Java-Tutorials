package com.jett.java.util.random;

import java.util.Random;

/**
 * 随机数
 * 开区间：边界的两个值不包括在内，常写成(a,b)
 * 闭区间：边界的两个值包括在内，常写成[a,b]
 * @author jett
 */
public class RandomTest {
    public static int count = 20;
    
    public static void main(String[] args) {
    
        // 随机种子，种子数只是随机算法的起源数字，和将要生成的随机数的区间没有任何关系。
        long seed = System.currentTimeMillis();
        java.util.Random random = new Random(seed);
        
        System.out.println("1、生成一个int");
        // 边界，生成区间 [0, bound)
        int bound = 5;
        for (int i = 0; i < count; i++) {
            System.out.println(random.nextInt(bound));
        }
    
        System.out.println("注意：如果种子相同，生成的随机数将一样。（同一环境下）");
        java.util.Random random1 = new Random(seed);
        java.util.Random random2 = new Random(seed);
        for (int i = 0; i < count; i++) {
            int i1 = random1.nextInt();
            int i2 = random2.nextInt();
            System.out.println(i1 + " = " + i2 + " => " + (i1 == i2));
        }
    
        // 2、java.lang.Math.random() 生成一个[0~1)的double值，包含0，不包含1
        // 只是一个静态方法，内部就是一个 Random().nextDouble()
        System.out.println("===== java.lang.Math.random()");
        for (int i = 0; i < count; i++) {
            System.out.println(java.lang.Math.random());
        }
    
        // 取得[0,1)之间的随机小数
        System.out.println(random.nextDouble());
        // 取得[x,y)之间的随机小数，公式：r.nextDouble() * (y-x) + x
        // e.g. [4,18)
        System.out.println("===== [x,y) 小数");
        for (int i = 0; i < count; i++) {
            System.out.println(random.nextDouble() * (18 - 4) + 4);
        }
        
        // [0,10)区间的整数
        // 方法1，通过原生API实现
        System.out.println(random.nextInt(10));
        // 方法2，通过求余
        System.out.println(Math.abs(random.nextInt() % 10));
        // [x, y)区间整数
        // e.g. [7,13)
        System.out.println("===== [x, y)区间整数");
        for (int i = 0; i < count; i++) {
            System.out.println(random.nextInt(13 - 7) + 7);
        }
        
    }
}
