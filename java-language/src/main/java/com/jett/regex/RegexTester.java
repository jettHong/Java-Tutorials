package com.jett.regex;

/**
 * 正则表达式-demo
 * @author jett
 */
public class RegexTester {
    /**
     * 别名KEY
     * 数字,字母,下划线
     */
    public static final String ALIAS_KEY = "^[A-Za-z0-9_]+$";
    
    public static void main(String[] args) {
        String[] values = {"absd_123", "1-3", "()0287"};
        for (String str : values) {
            System.out.println(str.matches(ALIAS_KEY) + "\t" + "str=" + str);
        }
    }
}
