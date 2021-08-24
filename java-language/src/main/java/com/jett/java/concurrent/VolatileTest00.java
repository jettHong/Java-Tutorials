package com.jett.java.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示在 volatile 作用。
 * 1、没有 volatile 修饰的场景
 * 2、有 volatile 修饰的场景
 *
 * @author jett
 */
public class VolatileTest00 implements Runnable {
    /**
     * 加了 static 修饰呢？
     */
    private volatile boolean stop = false;
    
    public void stopMe() {
        this.stop = true;
    }
    
    @Override
    public void run() {
        int i = 0;
        while (!stop) {
            i++;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        VolatileTest00 v = new VolatileTest00();
        System.out.println("开始");
        v.run();
        Thread.sleep(2 * 1000);
        v.stopMe();
        Thread.sleep(2 * 1000);
    }
    
}