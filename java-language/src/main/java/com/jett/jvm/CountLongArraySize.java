package com.jett.jvm;

import java.math.BigDecimal;

public class CountLongArraySize {
    public static void main(String[] args) throws InterruptedException {
        int size = 5000 * 10000;
        BigDecimal temp = new BigDecimal("123456789.123456789");
        BigDecimal[] values = new BigDecimal[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = temp; // new BigDecimal("123456789.123456789");
            if (i % 1000000 == 0) {
                System.out.println("i=" + i);
            }
        }
        while (true) {
            Thread.sleep(1000);
        }
    }
}
