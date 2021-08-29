package com.jett.java.proxy;

/**
 * 动态代理
 * REF：https://www.bilibili.com/video/BV1WE411d7Dv?p=19
 */
public class ProxyTest {
    
    public static void main(String[] args) {
        HumanImpl humanImpl = new HumanImpl();
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(humanImpl);
        Human human = (Human) pih.getProxy();
        human.say();
        
    }
    
    
}
