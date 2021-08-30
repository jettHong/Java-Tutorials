package com.jett.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyInvocationHandler implements InvocationHandler {
    
    // 被代理的实现类
    private Object object;
    
    /**
     * 构造函数，传入目标类
     */
    public ProxyInvocationHandler(Object target) {
        this.object = target;
    }
    
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                this
        );
    }
    
    
    private void before(Object[] args) {
        System.out.println("调用方法前：" + args);
    }
    
    private void after(Object result) {
        System.out.println("调用方法后：" + result);
    }
    
    @Override
    public Object invoke(java.lang.Object proxy, Method method, java.lang.Object[] args) throws Throwable {
        this.before(args);
        Object result = method.invoke(object, args);
        this.after(result);
        return result;
    }
}
