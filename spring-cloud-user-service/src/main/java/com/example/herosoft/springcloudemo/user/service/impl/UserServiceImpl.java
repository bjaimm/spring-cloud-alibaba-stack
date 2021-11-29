package com.example.herosoft.springcloudemo.user.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springcloudemo.user.dao.UserDao;
import com.example.herosoft.springcloudemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<ShopUser> findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}
