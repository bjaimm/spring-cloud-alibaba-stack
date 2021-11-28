package com.example.herosoft.springclouddemo.order.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<ShopOrder,Integer> {
}
