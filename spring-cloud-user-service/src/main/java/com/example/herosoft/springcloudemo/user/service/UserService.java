package com.example.herosoft.springcloudemo.user.service;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<ShopUser> findUserByName(String username);
}
