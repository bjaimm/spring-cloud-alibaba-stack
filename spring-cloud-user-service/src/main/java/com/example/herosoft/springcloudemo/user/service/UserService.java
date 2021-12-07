package com.example.herosoft.springcloudemo.user.service;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ShopUser findUserByName(String username);
    ShopUser findUserById(Integer userId);
    List<Role> findRolesByUserId(Integer userid);
}
