package com.jett.java.concurrent.volatile_test;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示启动多个线程，volatile只能保证可见性，无法保证原子性。
 * 程序写race自增根据计算 THREADS_COUNT * THREADS_TIME =  200_000
 * 但实际结果总小于上面的数值。
 *
 * 解决方法：
 * 1、粗暴地使用 synchronized 解决并发问题。
 * 2、使用Java并发包原子操作类。（getAndIncrement->getAndAddInt->compareAndSwapInt）
 *
 * @author jett
 */
public class VolatileTest {
    
    public static volatile int race = 0;
    
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    private static final int THREADS_COUNT = 20;
    
    private static final int THREADS_TIME = 10_000;
    
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(THREADS_COUNT);
    
    /**
     * 不加锁
     */
    public static void increase() {
        race++;
    }
    
    /**
     * 加锁 synchronized
     */
    public synchronized static void synchronizedIncrease() {
        race++;
    }
    /**
     *
     */
    public synchronized static void atomicIncrease() {
        // 自增1
        atomicInteger.getAndIncrement();
    }
    
    /**
     * 演示启动多个线程，volatile只能保证可见性，无法保证原子性。
     *
     * @throws InterruptedException
     */
    @Test
    public void increaseTest() throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < THREADS_TIME; i1++) {
                    increase();
                }
                COUNT_DOWN_LATCH.countDown();
            });
            threads[i].start();
        }
        COUNT_DOWN_LATCH.await();
        // 总小于 200000
        System.out.println(race);
    }
    
    /**
     * 解决方法：
     * 1、粗暴地使用 synchronized 解决并发问题。
     * @throws InterruptedException
     */
    @Test
    public void synchronizedIncreaseTest() throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < THREADS_TIME; i1++) {
                    synchronizedIncrease();
                }
                COUNT_DOWN_LATCH.countDown();
            });
            threads[i].start();
        }
        COUNT_DOWN_LATCH.await();
        // 正常输出 200000
        System.out.println(race);
    }
    
    /**
     * 解决方法：
     * 2、使用Java并发包原子操作类。
     * @throws InterruptedException
     */
    @Test
    public void atomicTest() throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < THREADS_TIME; i1++) {
                    atomicIncrease();
                }
                COUNT_DOWN_LATCH.countDown();
            });
            threads[i].start();
        }
        COUNT_DOWN_LATCH.await();
        // 正常输出 200000
        System.out.println(atomicInteger);
    }
}
