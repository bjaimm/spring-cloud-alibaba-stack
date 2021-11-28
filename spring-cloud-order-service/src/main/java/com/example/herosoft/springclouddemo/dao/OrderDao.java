package com.example.herosoft.springclouddemo.dao;

import com.example.herosoft.domain.entity.ShopOrder;
import com.example.herosoft.domain.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<ShopOrder,Integer> {
}
