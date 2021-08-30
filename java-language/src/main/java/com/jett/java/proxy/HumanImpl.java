package com.jett.java.proxy;

class HumanImpl implements Human {
    @Override
    public String sayHi(String name) {
        this.test(name);
        return "hi" + name;
    }
    
    @Override
    public void test(String name) {
        System.out.println("进入test方法" + name);
    }
}
