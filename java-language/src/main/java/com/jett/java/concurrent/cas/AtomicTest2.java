package com.jett.java.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用 AtomicStampedReference 解决 ABA 问题。
 * @author jett
 */
public class AtomicTest2 {
    static AtomicStampedReference<Integer> stampRef = new AtomicStampedReference(10, 1);
    
    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = stampRef.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第1次版本号： " + stamp);
            stampRef.compareAndSet(10, 11, stampRef.getStamp(), stampRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第2次版本号： " + stampRef.getStamp());
            stampRef.compareAndSet(11, 10, stampRef.getStamp(), stampRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第3次版本号： " + stampRef.getStamp());
        }, "张三").start();
        new Thread(() -> {
            try {
                // 立即取得当前版本号，有可能是1、2
                int stamp = stampRef.getStamp();
                System.out.println(Thread.currentThread().getName() + " 第1次版本号： " + stamp);
                // 有可能修改过
                boolean isSuccess = stampRef.compareAndSet(10, 12, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName() + " 修改是否成功： " + isSuccess + " 当前版本 ：" + stampRef.getStamp());
                System.out.println(Thread.currentThread().getName() + " 当前实际值： " + stampRef.getReference());
                
                TimeUnit.SECONDS.sleep(2);
                // 取得当前版本号
                isSuccess = stampRef.compareAndSet(10, 12, stampRef.getStamp(), stampRef.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + " 修改是否成功： " + isSuccess + " 当前版本 ：" + stampRef.getStamp());
                System.out.println(Thread.currentThread().getName() + " 当前实际值： " + stampRef.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "李四").start();
    }
}
