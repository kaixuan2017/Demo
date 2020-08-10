package com.kx.aop.service.impl;

import com.kx.aop.entity.User;
import com.kx.aop.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void addUser(User user) {
        System.out.println("add user into db");
    }

    @Override
    public User getUser(int id) {
        User u= new User();
        u.setId(1);
        System.out.println("get user from db");
        return u;
    }
}
