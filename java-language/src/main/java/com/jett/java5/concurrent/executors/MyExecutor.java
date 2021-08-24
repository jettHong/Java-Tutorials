package com.jett.java5.concurrent.executors;

import java.util.concurrent.*;

/**
 * 执行
 *
 * @author jett
 */
public class MyExecutor extends Thread {
    
    private final int index;
    
    public MyExecutor(int i) {
        this.index = i;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("[" + this.index + "] start....");
            Thread.sleep((int) (Math.random() * 10 * 1000));
            System.out.println("[" + this.index + "] end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    
        // 方法一：（不推荐）Executors 静态函数生成一个固定的线程池，顾名思义，线程池的线程是不会释放的，即使它是Idle。
        ExecutorService service = Executors.newFixedThreadPool(4);
    
        // 方法二：自定义
//        ExecutorService service = new ThreadPoolExecutor(
//                2, // corePoolSize 表示常驻核心线程数
//                10, // maximumPoolSize 表示线程池能够容纳同时执行的最大线程数
//                30, // keepAliveTime 表示线程池中的线程空闲时间
//                TimeUnit.SECONDS, // TimeUnit 表示时间单位。
//                new LinkedBlockingQueue<Runnable>() // workQueue 表示缓存队列
//        );
        
        for (int i = 0; i < 10; i++) {
            service.execute(new MyExecutor(i));
            // service.submit(new MyExecutor(i));
        }
        System.out.println("submit finish");
        service.shutdown();
    }
    
}
