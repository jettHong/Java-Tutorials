package com.jett.java5;

import java.util.Arrays;
import java.util.List;

/**
 * 对 for i的简化
 * @author jett
 */
public class ForEach {
    
    public static void main(String[] args) {
        
        int[] arr = {1, 2, 5, 4, 8};
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        
        for (int i : arr) {
            System.out.println(arr[i]);
        }
        
        List<String> names = Arrays.asList("张三", "李四", "王五");
        for (String name : names) {
            System.out.println(name);
        }
        
    }
}
