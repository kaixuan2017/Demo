package com.kx.aop.test;

import com.kx.aop.entity.User;
import com.kx.aop.service.UserService;
import com.kx.aop.service.impl.UserServiceImpl;
import com.kx.aop.util.ProxyUtil;

import java.lang.reflect.Proxy;

/**
 * 测试JDK动态代理
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object proxyedObject  = new UserServiceImpl(); //被代理的对象

        ProxyUtil proxyUtil = new ProxyUtil(proxyedObject);

        UserService proxyObject = (UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                UserServiceImpl.class.getInterfaces(), proxyUtil);

        proxyObject.addUser(new User());
        proxyObject.getUser(1);
    }
}
