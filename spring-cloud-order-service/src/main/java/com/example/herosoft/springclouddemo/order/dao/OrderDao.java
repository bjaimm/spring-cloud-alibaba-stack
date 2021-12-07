package com.example.herosoft.springclouddemo.order.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<ShopOrder,Integer> {
}
