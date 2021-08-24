package com.jett.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Java使用Lambda表达式遍历Collection集合
 *
 * @author jett
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List nums = new ArrayList();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        
        // 输出最大元素，将输出 3
        System.out.println(Collections.max(nums));
        
        // 输出最小元素，将输出-5
        System.out.println(Collections.min(nums));
        
        // 将 nums中的 0 使用 1 来代替
        Collections.replaceAll(nums, 0, 1);
        // [2, -5, 3, 1]
        System.out.println(nums);
        
        // 判断-5在List集合中出现的次数，返回1
        System.out.println(Collections.frequency(nums, -5));
        
        // 对 nums集合排序
        Collections.sort(nums);
        // [-5, 1, 2, 3]
        System.out.println(nums);
        
        // 二分法查询，只有排序才准确
        // 输出3
        System.out.println(Collections.binarySearch(nums, 3));
        
        // 将list设置为不可修改
        List newNums = Collections.unmodifiableList(nums);
        // 旧 list 仍可修改，并影响到新的 list，可以将复制一个新list。
        nums.add(8);
        System.out.println(newNums);
        try {
            newNums.add(9);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("newNums" + newNums);
        
    }
}
