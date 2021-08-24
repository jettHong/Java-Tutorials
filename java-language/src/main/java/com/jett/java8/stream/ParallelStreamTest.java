package com.jett.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

/**
 * stream 和 parallelStream
 * 分别代表了串行执行模式和并行执行模式。当使用并行执行模式时，底层使用的是JDK7中引入的Forn/Join Framework。
 * 不建议在生产代码中使用parallelStream，会引发不可预料的后果。还是老老实实地使用线程池更加靠谱。
 * REF：
 * https://blog.csdn.net/u011001723/article/details/52794455
 * https://blog.csdn.net/dm_vincent/article/details/95661178
 *
 *
 * @author wangguangdong
 * @version v1.0
 * @description 这是一个用来让你更加熟悉parallelStream的原理的实力
 * @date 2016年10月11日18:26:55
 */
public class ParallelStreamTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        
        // 构造一个10000个元素的集合
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        
        // 统计并行执行list的线程
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 并行执行
        list.parallelStream().forEach(integer -> {
            Thread thread = Thread.currentThread();
            // System.out.println(thread);
            // 统计并行执行list的线程
            threadSet.add(thread);
        });
        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
        System.out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(i);
            list2.add(i);
        }
        Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread threadA = new Thread(() -> {
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                // System.out.println("list1" + thread);
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });
        Thread threadB = new Thread(() -> {
            list2.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                // System.out.println("list2" + thread);
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });
        
        threadA.start();
        threadB.start();
        countDownLatch.await();
        System.out.print("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
        
        System.out.println("---------------------------");
        System.out.println(threadSet);
        System.out.println(threadSetTwo);
        System.out.println("---------------------------");
        threadSetTwo.addAll(threadSet);
        System.out.println(threadSetTwo);
        System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
        System.out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
    }
}
