package com.kx.aop.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理工具类
 * JDK动态代理要求被代理实现一个接口，只有接口中的方法才能被代理。
 * 方法是：将被代理对象注入到一个中间对象，而中间对象实现InvocationHandler接口
 * 在实现该接口时，可以在被代理对象调用它的方法时，在调用的前后插入一些代码。
 */
public class ProxyUtil implements InvocationHandler {
    private Object target; //被代理的对象

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something begore-----");
        Object result = method.invoke(target, args);
        System.out.println("do sth after++++++++");
        return result;
    }

    public ProxyUtil(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
