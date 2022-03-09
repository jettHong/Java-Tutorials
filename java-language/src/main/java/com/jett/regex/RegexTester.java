package com.jett.regex;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Validator;
import org.junit.Test;

import java.util.regex.Matcher;
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
        // 方法1
        for (String str : VALUES) {
            System.out.println(str.matches(REGULAR) + "\t" + "str=" + str);
        }
        
        // 方法2（推荐）
        Pattern p = Pattern.compile(REGULAR);
        for (String str : VALUES) {
            System.out.println(p.matcher(str).matches() + "\t" + "str=" + str);
        }
    }
    
    /**
     * 按指定模式在字符串查找
     */
    @Test
    public void group() {
        String[] lines = {"6-1负载均衡的两种方式【购课加q123123123】.mp4", "6-16扩展Ribbon-基于元数据的版本控制.mp4"};
        // "^(\\d+)-(\\d+)(\\S*)"
        // "^(\\d+)-(\\d+)(\\S*)"
        String patternStr = "^(\\d+)-(\\d+)(.*)($)";
        
        // 创建 Pattern 对象
        Pattern regex = Pattern.compile(patternStr);
        
        // 现在创建 matcher 对象
        for (String line : lines) {
            Matcher m = regex.matcher(line);
            try {
                if (m.find()) {
                    for (int i = 0; i < m.groupCount(); i++) {
                        System.out.println(String.format("G%s: %s", i, m.group(i)));
                    }
                    String pre = m.group(1) + "-" + m.group(2);
                    System.out.println(pre);
                } else {
                    System.out.println("NO MATCH");
                }
                System.out.println("------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 在 for 里面 Pattern.compile 效率低。
     *
     * @throws InterruptedException
     */
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
    
    
    @Test
    public void money() throws InterruptedException {
        String[] moneys = {
                "123.456", // 尾部超过2位
                "0123.00", // 头部有0
                "F23.00", // 有非数值类型
                "-123.00", // 非正数
                "12345678901234567890.00", // 整数部分超过 18 位
                "123.45",   //
                "123"   //
        };
    
        Pattern p = Pattern.compile("^[0-9]{0,18}.[0-9]{0,2}$"); // 仅考虑
        for (String str : moneys) {
            System.out.println(str + "\t=\t" +p.matcher(str).matches());
        }
    
    
        System.out.println("-----------------");
        
        for (String str : moneys) {
            System.out.println(str + "\t=\t" + Validator.isMoney(str));
        }
    }
    
    
}
