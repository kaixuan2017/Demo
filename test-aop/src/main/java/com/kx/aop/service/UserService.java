package com.kx.aop.service;

import com.kx.aop.entity.User;

public interface UserService {
    void addUser(User user);

    User getUser(int id);
}
