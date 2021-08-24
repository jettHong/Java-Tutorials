package com.jett.jvm;

import cn.hutool.extra.cglib.CglibUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 一、栈溢出
 * 解决方法：
 * 增加堆大小、及时释放内存（无用对象）
 */
public class OomStack {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            // CglibBean bean = new CglibBean();
        }
        
        // java.lang.OutOfMemoryError: Java heap space
    }
}
