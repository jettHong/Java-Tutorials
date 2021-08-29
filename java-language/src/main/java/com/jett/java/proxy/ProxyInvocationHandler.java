package com.jett.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyInvocationHandler implements InvocationHandler {
    
    // 被代理的实现类
    private Object impl;
    
    public void setTarget(Object impl) {
        this.impl = impl;
    }
    
    public Object getProxy() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                impl.getClass().getInterfaces(),
                this
        );
    }
    
    @Override
    public Object invoke(java.lang.Object proxy, Method method, java.lang.Object[] args) throws Throwable {
        return method.invoke(impl, args);
    }
}
