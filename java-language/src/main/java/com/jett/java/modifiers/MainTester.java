package com.jett.java.modifiers;

/**
 * 测试主类
 *
 * @author jett
 */
public class MainTester {
    public static void main(String[] args) {
        BaseClass base = new BaseClass();
        // System.out.println(base.strPrivate); // 'strPrivate' has private access in 'com.jett.java.modifiers.BaseClass'
        System.out.println(base.strProtected);
    
        BaseClass sub = new SubClass();
        // sub.strprivate = "111"; // Cannot resolve symbol 'strprivate'
        
    }
}
