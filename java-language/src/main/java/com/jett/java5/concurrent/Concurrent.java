package com.jett.java5.concurrent;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发库
 * @author jett
 */
public class Concurrent {
    
    public void lock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("hi!");
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        HashMap map = new HashMap();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        
    }
    
}
