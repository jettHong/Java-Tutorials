package com.jett.regex;

import cn.hutool.core.date.StopWatch;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * 正则表达式-demo
 *
 * @author jett
 */
public class RegexTester {
    
    /**
     * 别名KEY
     * 数字,字母,下划线
     */
    public static final String REGULAR = "^[A-Za-z0-9_]+$";
    public static final String[] VALUES = {"absd_123", "1-3", "()0287"};
    
    
    public static void main(String[] args) {
        for (String str : VALUES) {
            System.out.println(str.matches(REGULAR) + "\t" + "str=" + str);
        }
        
        Pattern p = Pattern.compile(REGULAR);
        for (String str : VALUES) {
            System.out.println(p.matcher(str).matches() + "\t" + "str=" + str);
        }
    }
    
    @Test
    public void ugly() throws InterruptedException {
        Thread.sleep(2 * 1000);
        StopWatch watch = new StopWatch();
        watch.start();
        
        for (int i = 0; i < 1000000; i++) {
            Pattern p = Pattern.compile(REGULAR);
            for (String str : VALUES) {
                p.matcher(str).matches();
            }
        }
        
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTotalTimeMillis());
        // Time Elapsed: 977
        // Time Elapsed: 989
        // Time Elapsed: 972
    }
    
    public static final Pattern P_REG = Pattern.compile(REGULAR);
    
    @Test
    public void optimize() throws InterruptedException {
        Thread.sleep(2 * 1000);
        StopWatch watch = new StopWatch();
        watch.start();
        
        for (int i = 0; i < 1000000; i++) {
            for (String str : VALUES) {
                P_REG.matcher(str).matches();
            }
        }
        
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTotalTimeMillis());
        // Time Elapsed: 606
        // Time Elapsed: 572
        // Time Elapsed: 653
    }
    
    
}
