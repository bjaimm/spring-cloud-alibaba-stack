package com.example.herosoft.springcloudemo.user.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<ShopUser,Integer> {
}
