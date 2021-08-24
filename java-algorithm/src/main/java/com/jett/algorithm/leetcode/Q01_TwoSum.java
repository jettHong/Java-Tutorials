package com.jett.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 链接：https://leetcode-cn.com/problems/two-sum
 */
public class Q01_TwoSum {
    public static void main(String[] args) {
        int[] nums = {4, 2, 6, 3, 7, -1};
        int target = 8;
        {
            int[] ans1 = Q01_TwoSum.twoSum(nums, target);
            System.out.println(ans1[0] + "," + ans1[1]);
        }
        {
            int[] ans2 = Q01_TwoSum.twoSumByMap(nums, target);
            System.out.println(ans2[0] + "," + ans2[1]);
        }
    }
    
    /**
     * 解法一：
     * 暴力双重循环查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = (i + 1); j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }
    
    /**
     * 解法二：
     * 巧妙使用 Hash 原理，将原来第二层循环替换成一次hash查找（这里用Map实现）。
     * key-数值 : value-数值下标
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumByMap(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub) && map.get(sub) != i) {
                ans[0] = i;
                ans[1] = map.get(sub);
                break;
            }
        }
        return ans;
    }
    
    
}
