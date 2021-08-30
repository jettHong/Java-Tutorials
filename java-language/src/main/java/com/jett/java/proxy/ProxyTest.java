package com.jett.java.proxy;

/**
 * 动态代理
 * REF：
 * https://www.bilibili.com/video/BV1WE411d7Dv?p=19
 * https://zhuanlan.zhihu.com/p/149696706
 */
public class ProxyTest {
    
    public static void main(String[] args) {
        // 动态代理的对象是在运行时期创建的，没办法通过打断点的方式进行分析
        // 将动态代理对象的 class 文件写入到磁盘中
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        
        ProxyInvocationHandler pih = new ProxyInvocationHandler(new HumanImpl());
        Human human = pih.getProxy();
        System.out.println(human.sayHi("小东"));
    }
    
}
