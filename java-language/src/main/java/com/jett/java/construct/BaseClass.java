package com.jett.java.construct;

import lombok.Data;

/**
 * 在 Java 中定义一个不做事且没有参数的构造方法的作用
 * Java 程序在执行子类的构造方法之前，如果没有用 super()来调用父类特定的构造方法，则会调用父类中“没有参数的构造方法”。
 * 因此，如果父类中只定义了有参数的构造方法，而在子类的构造方法中又没有用 super()来调用父类中特定的构造方法，则编译时将发生错误，
 * 解决办法是在父类里加上一个不做事且没有参数的构造方法。
 *
 * @author jett
 */
@Data
public class BaseClass {
    
    String name;
    
    public BaseClass(String name) {
        this.name = name;
    }

    public BaseClass() {
        this.name = "无参数";
    }
}
