package com.jett.java.util.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Arrays 类的常见用法：
 * 1、toString 转字符串，方便打印
 * 2、binarySearch 对半查找
 * 3、copyOf 复制数组
 * 4、fill 填充数组
 * 5、parallel 一类的 Java 8 增强
 * 6、数据转列表，转换后的列表不能进行添加、删除操作
 * 7、
 *
 * @author jett
 */
public class ArraysTest {
    
    @Test
    public void main() {
        
        long[] longs = {1, 2, 3, 4, 5, 6, 7, 7, 8, 9};
        
        // 将一个数组转换成一个字符串，多个数组元素使用英文逗号,和空格隔开
        System.out.println(Arrays.toString(longs));
        
        // 使用二分法查询 key 元素值在 a 数组中出现的索引，如果 a 数组不包含 key 元素值，则返回负数。
        // 调用该方法时要求数组中元素己经按升序排列，这样才能得到正确结果。
        System.out.println(Arrays.binarySearch(longs, 5));
        
        // 复制返回一个新数组
        // copyOf,类似有的 copyOfRange 范围内
        // 第二参数为长度，新数组长于旧数组时，填充默认值
        long[] newLongs = Arrays.copyOf(longs, 5);
        System.out.println(Arrays.toString(newLongs));
        
        // 填充数组
        Arrays.fill(newLongs, -1);
        System.out.println(Arrays.toString(newLongs));
        
        // Java 8 增强了 Arrays 类的功能，所有以 parallel 开头的方法都表示该方法可利用 CPU 并行的能力来提高性能
        // 比如：parallelSort(T[] array)
        long[] r = {-1, 9, 7, 4, 3, 1, 2, 5, 8, 6, 0};
        Arrays.parallelSort(r);
        System.out.println(Arrays.toString(r));
    }
    
    
    /**
     * 将数组转成列表
     */
    @Test
    public void asList() {
        // 将数组转成列表
        List<String> strings = Arrays.asList("1", "2", "3");
        // 打印 [1, 2, 3]
        System.out.println(strings);
        
        // java.lang.UnsupportedOperationException
        // 因为Arrays.asList 返回的是内部实现的类 java.util.Arrays.ArrayList，不支持再操作（添加、移除）
        try {
            strings.add("4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 解决方法：使用 ArrayList 转换一次
        ArrayList newList = new ArrayList(strings);
        newList.add("4");
        System.out.println(newList);
    }
    
}
