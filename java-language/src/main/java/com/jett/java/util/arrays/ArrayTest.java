package com.jett.java.util.arrays;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组
 * 数组（array）是一种最简单的复合数据类型，
 * 它是有序数据的集合，数组中的每个元素具有相同的数据类型，
 * 可以用一个统一的数组名和不同的下标来确定数组中唯一的元素。
 * 根据数组的维度，可以将其分为一维数组、二维数组和多维数组等。
 * <p>
 * 特点：
 * 1、给定长度后，不可变。
 * 2、随机访问方便（通过索引值）
 * 3、删除、插入麻烦
 * 经常与列表List相比，优缺点相反。
 *
 * @author jett
 */
public class ArrayTest {
    
    
    public static void main(String[] args) {
        
        // 一、声明
        // 声明方式1
        int[] intArray01;
        // 声明方式2 不推荐，说是不方便一下子看出类型，编译麻烦什么的。
        String[] intArray02;
        // 声明后不能指向其它类型
        // intArray02 = intArray01;
        
        // 数组既可以存储基本类型的数据，也可以存储引用类型的数据
        Object[] objArray;
        
        // 二、赋值
        // 赋值1 new 方式需要给定长度值。
        int[] intArray03 = new int[8];
        // 赋值2 声明后直接赋值，不需要给定长度
        int[] intArray04 = {3, 5, 4, 6, 9, 0, 8, 1, 2, 0};
        
        // 操作
        // 取得数组长度
        System.out.println(intArray04.length);
        // 随机访问某个[索引|下标]里的数据
        System.out.println(intArray04[2]);
        // 试图访问不存在的下标时，会拋出 java.lang.ArrayIndexOutOfBoundsException 异常。
        try {
            System.out.println(intArray04[-1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 声明后，这个数级就只能放相应的类型，这个里要放入 String 会报错。
        // intArray04[0] = "111";
        // 数组是内置，无法点击查看实现，但它确是引用传递
        
        // 数组引用传递
        // 那么05指向04，即对05进行操作，04也跟着变更（栈），因为是同一个东西（堆），(一个堆内存被不同的栈内存所指向）。
        int[] intArray05 = intArray04;
        System.out.println(Arrays.toString(intArray04));
        intArray05[intArray05.length - 1] = -1;
        System.out.println(Arrays.toString(intArray04));
        
        //排序
        // 排序前后打印出来
        System.out.println(Arrays.toString(intArray04));
        java.util.Arrays.sort(intArray04);
        System.out.println(Arrays.toString(intArray04));
        
        // 删除数值，数值往前移动，最尾重置为0（并不科学）
        ArrayTest.del(intArray04, 3);
        System.out.println(Arrays.toString(intArray04));
        
        // 初始化二维数组
        int[][] arrayTwo = new int[][]{{1, 2}, {3, 4}};
        
        //TODO 数据操作，合并，删除，去重，排序
        
    }
    
    /**
     * 演示，扩展数组长度
     */
    @Test
    public void inc() {
        int num = 3;
        int[] src = {0, 1, 2, 3};
        int[] dest = new int[src.length + num];
        System.arraycopy(src, 0, dest, 0, src.length);
        System.out.println(Arrays.toString(dest));
    }
    
    /**
     * 模拟删除，尾部无法正常删除。
     *
     * @param in
     * @param index
     */
    public static void del(int[] in, Integer index) {
        // 直接返回：数组为空、长度小于1、索引不在数组范围内
        if (in == null || in.length < 1 || index < 0 || index > in.length) {
            return;
        }
        for (int i = index; i < in.length - 2; i++) {
            in[i] = in[i + 1];
        }
        in[in.length - 1] = 0;
    }
    
    /**
     * 二维数组
     */
    public void twoDimensionTest() {
        // 定义一个二维数组
        int[][] a;
        // 把a当成一维数组进行初始化，初始化a是一个长度为4的数组
        // a数组的数组元素又是引用类型
        a = new int[4][];
        // 把a数组当成一维数组，遍历a数组的每个数组元素
        for (int i = 0, len = a.length; i < len; i++) {
            System.out.println(a[i]);
            // 输出 null null null null
        }
        
        // 初始化a数组的第一个元素
        a[0] = new int[2];
        
        // 访问a数组的第一个元素所指数组的第二个元素
        a[0][1] = 6;
        
        // a数组的第一个元素是一个一维数组，遍历这个一维数组
        for (int i = 0, len = a[0].length; i < len; i++) {
            System.out.println(a[0][i]);
            // 输出 0 6
        }
    }
}
