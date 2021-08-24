package com.jett.java8.interface_default_method.test01;

interface InterfaceA {
    default void foo() {
        System.out.println("Interface A foo");
    }
}

interface InterfaceB extends InterfaceA {
    // 接口B 继承于 接口A 但没有自己的实现。
}

interface InterfaceC extends InterfaceA {
    // 接口C 继承于 接口A 重写自己的实现。
    @Override
    default void foo() {
        System.out.println("Interface C foo");
    }
}

interface InterfaceD extends InterfaceA {
    // 覆写默认方法并将它重新声明为抽象方法，这样新接口的子类必须再次覆写并实现这个抽象方法。
    @Override
    void foo();
}


public class Test01 {
    public static void main(String[] args) {
        
        new InterfaceB() {
        }.foo(); // 打印：“Interface A foo”
        
        new InterfaceC() {
        }.foo(); // 打印：“Interface C foo”
        
        new InterfaceD() {
            @Override
            public void foo() {
                System.out.println("Interface D foo");
            }
        }.foo(); // 打印：“InterfaceD foo”
        
        // 或者使用 lambda 表达式
        ((InterfaceD) () -> System.out.println("Interface D foo - lambda")).foo(); // 打印：“Interface D foo - lambda”
        
    }
}