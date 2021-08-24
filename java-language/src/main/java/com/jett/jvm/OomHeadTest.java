package com.jett.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 一、堆溢出
 * 解决方法：
 * 增加堆大小、及时释放内存（无用对象）
 */
public class OomHeadTest {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(new byte[1024 * 1024]);
        }
        
        // java.lang.OutOfMemoryError: Java heap space
    }
}
