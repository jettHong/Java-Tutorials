package com.jett.java.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ABA：当前线程的CAS操作无法分辨当前V值是否发生过变化，因为期间可能另一个线程修改过V值（由原来是A，先修改成B，再修改回成A）。
 * JDK1.5以后，推出了两种办法解决或改善ABA问题。分别是：
 *  java.util.concurrent.atomic.AtomicStampedReference
 *  java.util.concurrent.atomic.AtomicMarkableReference
 * @author jett
 * REF：https://blog.csdn.net/jingzi123456789/article/details/78004074
 */
@SuppressWarnings("ALL")
public class AtomicTest1 {
    private static final AtomicInteger INDEX = new AtomicInteger(10);
    
    /**
     * 1、ABA问题进行场景重现
     * @param args
     */
    public static void main(String[] args) {
        INDEX.getAndAdd(1);
        new Thread(() -> {
            INDEX.compareAndSet(10, 11);
            INDEX.compareAndSet(11, 10);
            System.out.println(Thread.currentThread().getName() +
                    "：10->11->10");
        }, "张三").start();
        
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                // Atomically sets the value to the given updated value if the current value == the expected value.
                boolean isSuccess = INDEX.compareAndSet(10, 12);
                System.out.println(Thread.currentThread().getName() +
                        "：index是预期的10嘛，" + isSuccess
                        + "   设置的新值是：" + INDEX.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "李四").start();
    }
}
