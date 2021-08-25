package com.jett.util;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * 计算耗时的方法
 * 1、使用JDK原生方法，缺点需要自己打变量做记录
 * System.currentTimeMillis()
 * System.nanoTime()
 * Instant.now()
 *
 * 2、使用各中工具类，简洁
 * cn.hutool.core.date.StopWatch
 * org.apache.commons.lang3.time.StopWatch
 * org.springframework.util.StopWatch
 *
 * @author:jett
 * @date:2021-08-24
 */
public class StopWatchTest {
    
    /**
     * 使用JDK原生 System.currentTimeMillis()
     *
     * @throws InterruptedException
     */
    @Test
    public void currentTimeMillis() throws InterruptedException {
        long start = System.currentTimeMillis();
        
        // do something like sleep
        Thread.sleep(2 * 1000);
        
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }
    
    /**
     * 使用JDK原生 System.nanoTime()
     *
     * @throws InterruptedException
     */
    @Test
    public void nanoTime() throws InterruptedException {
        long start = System.nanoTime();
        
        // do something like sleep
        Thread.sleep(2 * 1000);
        
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }
    
    /**
     * 使用JDK原生 Instant.now()
     *
     * @throws InterruptedException
     */
    @Test
    public void instant() throws InterruptedException {
        Instant start = Instant.now();
        
        // do something like sleep
        Thread.sleep(2 * 1000);
        
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println(timeElapsed);
    }
    
    /**
     * 使用 cn.hutool.core.date.StopWatch
     *
     * @throws InterruptedException
     */
    @Test
    public void hutoolWatch() throws InterruptedException {
        cn.hutool.core.date.StopWatch watch = new cn.hutool.core.date.StopWatch();
        watch.start();
        
        // do something like sleep
        Thread.sleep(2 * 1000);
        
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTotalTimeMillis());
    }
    
    /**
     * 使用 org.apache.commons.lang3.time.StopWatch
     *
     * @throws InterruptedException
     */
    @Test
    public void commonWatch() throws InterruptedException {
        org.apache.commons.lang3.time.StopWatch watch = new org.apache.commons.lang3.time.StopWatch();
        watch.start();
        
        // do something like sleep
        Thread.sleep(2 * 1000);
        
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTime(TimeUnit.MILLISECONDS));
    }
    
    /**
     * 使用 org.springframework.util.StopWatch
     *
     * @throws InterruptedException
     */
    @Test
    public void springWatch() throws InterruptedException {
        org.springframework.util.StopWatch watch = new org.springframework.util.StopWatch();
        watch.start();
        
        // do something like sleep
        Thread.sleep(2 * 1000);
        
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTotalTimeMillis());
    }
    
    
}
