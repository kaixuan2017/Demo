package com.kx.aop.util;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib：code generate libary.
 * 字节码生成技术实现AOP，其实就是继承被代理对象，然后Override被代理的方法，在覆盖该方法时，自然可以插入我们自己的代码。
 * 因为需要Override被代理对象的方法，这就必须要求被代理方法不能时final方法，因为final不能被子类覆盖。
 */
public class CGProxyUtil implements MethodInterceptor {
    private Object target;

    public CGProxyUtil(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try {
            before();
            result = methodProxy.invokeSuper(o, objects);
            after();
        } catch (Throwable throwable) {
            exception();
        } finally {
            beforeReturning();
        }
        return result;
    }


    public Object getProxyObject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass()); //设置父类
        enhancer.setCallback(this); //设置回调，在调用父类方法时回调this.intercept()
        return enhancer.create(); //创建代理对象
    }

    private void before() {
        System.out.println("do sth before------------");
    }
    private void after() {
        System.out.println("do sth after------------");
    }
    private void exception() {
        System.out.println("do sth exception------------");
    }
    private void beforeReturning() {
        System.out.println("do sth beforeReturning------------");
    }
}
