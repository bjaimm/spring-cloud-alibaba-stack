package com.example.herosoft.springclouddemo.dao;

import com.example.herosoft.domain.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<ShopUser,Integer> {
}