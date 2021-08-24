package com.jett.java8.interface_default_method.test03;

/**
 * 接口继承行为发生冲突时的解决规则
 */

interface InterfaceA {
    default void foo() {
        System.out.println("Interface A foo");
    }
}

interface InterfaceB extends InterfaceA {
    @Override
    default void foo() {
        System.out.println("Interface B foo");
    }
}

// 正确
class ClassA implements InterfaceA, InterfaceB {
}

class ClassB implements InterfaceA, InterfaceB {
    @Override
    public void foo() {
//        InterfaceA.super.foo(); // 错误
        InterfaceB.super.foo();
    }
}


public class Test02 {
}
