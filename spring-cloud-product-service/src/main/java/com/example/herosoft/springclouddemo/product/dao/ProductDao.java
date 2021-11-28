package com.example.herosoft.springclouddemo.product.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<ShopProduct,Integer> {
}
