package com.jett.algorithm.fibonacci.sequence;

import org.junit.Assert;

/**
 * 青蛙跳台阶问题暨斐波那契数列
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * https://blog.csdn.net/K346K346/article/details/52576680
 *
 * @author jett
 */
public class FibMain {
    public static void main(String[] args) {
        FibMain main = new FibMain();
        System.out.println(fib(3));
        // n == 3
        // => 1,1,1
        // => 1,2
        // => 2,1
        
        System.out.println(fib(4));
        // n == 4
        // => 1,1,1,1
        // => 1,1,2
        // => 1,2,1
        // => 2,1,1
        // => 2,2
        
        System.out.println(fib(5));
        // n == 5
        // => 1,1,1,1,1
        // => 1,1,1,2
        // => 1,1,2,1
        // => 1,2,1,1
        // => 2,1,1,1
        // => 1,2,2
        // => 2,1,2
        // => 2,2,1
    
        System.out.println("递归法 = " + fibRecursion(5));
        System.out.println("迭代法 = " + fibIterator(5));
    }
    
    /**
     * 初级版本
     *
     * @param n
     * @return
     */
    public static Integer fib(Integer n) {
        if (n <= 0) {
            return -1;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    
    
    /**
     * 递归法
     * 时间复杂度O(n),空间复杂度O(n)
     * @param n
     * @return
     */
    public static int fibRecursion(int n) {
        if (n <= 0) {
            return -1;
        } else if (n == 1) {
            return 1;
        }
        return 2 * fibRecursion(n - 1);
    }
    
    
    /**
     * 迭代法
     * 时间复杂度O(n),空间复杂度O(1)
     * @param n
     * @return
     */
    public static int fibIterator(int n) {
        int res = 1;
        if (n <= 0) {
            return -1;
        } else if (n == 1) {
            return 1;
        }
        for (int i = 2; i <= n; ++i) {
            res = 2 * res;
        }
        return res;
    }
}
