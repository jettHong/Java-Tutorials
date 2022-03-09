package com.jett.java.lang;

import com.jett.SampleBean;

public class BaseTypeTest {
    public static void main(String[] args) {
        SampleBean bean = new SampleBean();
        if (bean.getAge() == null) {
            System.out.println("123");
        }
        System.out.println(Long.MAX_VALUE);
        System.out.println(System.currentTimeMillis());
    }
}
