package com.kx.aop.test;

import com.kx.aop.entity.User;
import com.kx.aop.service.UserService;
import com.kx.aop.service.impl.UserServiceImpl;
import com.kx.aop.util.CGProxyUtil;

/**
 * 测试CGlib代理
 */
public class CGProxyTest {

    public static void main(String[] args) {
        Object proxyedObject  = new UserServiceImpl();
        CGProxyUtil cgProxyUtil = new CGProxyUtil(proxyedObject);
        UserService proxyObject = (UserService) cgProxyUtil.getProxyObject();

        proxyObject.getUser(1);
        proxyObject.addUser(new User());
    }
}
