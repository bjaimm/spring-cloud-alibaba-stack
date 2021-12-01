package com.example.herosoft.springcloudemo.user.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springcloudemo.user.dao.RoleDao;
import com.example.herosoft.springcloudemo.user.dao.UserDao;
import com.example.herosoft.springcloudemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public ShopUser findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public List<Role> findRolesByUserId(Integer userid) {
        return roleDao.findRolesByUserId(userid);
    }
}
