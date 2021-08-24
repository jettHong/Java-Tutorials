package com.jett.java5;

import java.util.Arrays;
import java.util.List;

/**
 * 可变参数
 *
 * @author jett
 */
public class VarParameter {
    
    
    public static void main(String[] args) {
        
        
        List list = Arrays.asList(new int[]{1, 2, 3});
        System.out.println(list.size()); // 输出1
        
        printArr(1, 2, 3); // 3，隐式地构造args = new Object[]{1, 2, 3}
        printArr(new Integer[]{1, 2, 3});  // 3，隐式地将Integer数组转变为Object数组
        printArr(new int[]{1, 2, 3}); // 1，将int[]转变为Object，因此Object数组的长度只有一（基本类型无法直接转变为Object）
        printArr("1", "2", "3"); // 3
        printArr(new String[]{"1", "2", "3"}); // 3
        printArr(new Object[]{new String[]{"1", "2", "3"}}); // 1，将String数组作为Object数组的一个元素进行传递
        printArr((Object) new String[]{"1", "2", "3"}); // 1
    }
    
    private static void printArr(Object... args) {
        System.out.println(args.length);
    }
}