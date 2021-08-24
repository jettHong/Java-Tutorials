package com.jett.jvm;

/**
 * 先指定运行参数，再观察输出。
 * -Xmx20m -Xms5m
 * @author jett
 */
public class JVMHeapTest {
    public static void main(String[] args) {
        int step = 1024;
        
        long xmx = Runtime.getRuntime().maxMemory() / step / step;
        System.out.println("Xmx（可达总量） = " + xmx + " M");
        
        long freeMem = Runtime.getRuntime().freeMemory() / step / step;
        System.out.println("freeMem（当前剩余） = " + freeMem + " M");
        
        long totalMemory = Runtime.getRuntime().totalMemory() / step / step;
        System.out.println("totalMemory（当前总量） = " + totalMemory + " M");
    }
}
