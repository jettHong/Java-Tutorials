package com.jett.java.util.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 线程安全的随机
 * @author jett
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalRandom.current().nextInt());
    }
}
