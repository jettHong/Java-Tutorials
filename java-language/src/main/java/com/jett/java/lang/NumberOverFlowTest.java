package com.jett.java.lang;

/**
 * 数值溢出
 * 在最大值再+1 => 数值为变成 负值（最小值）
 * 在最大值再+2 => 数值 向（正）最大值发展
 *
 * @author jett
 */
public class NumberOverFlowTest {
    public static void main(String[] args) {
        // 2147483647
        System.out.println(Integer.MAX_VALUE);
        {
            int num = 2147483647;
            num += 1L;
            // -2147483648
            System.out.println(num);
        }
        {
            int num = 2147483647;
            num += 2L;
            // -2147483647
            System.out.println(num);
        }
        {
            int num = 2147483647;
            num += 3L;
            // -2147483646
            System.out.println(num);
        }
        
        
        {
            int i = 1;
            int j = i++;
            if ((i == (++j)) && ((i++) == j)) {
                i += j;
            }
            System.out.println("i = " + i);
        }
    }
}
