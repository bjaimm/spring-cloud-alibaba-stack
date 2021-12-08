package com.example.herosoft.springclouddemo.product.service;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ShopProduct> findProductByName(String pname);

    ShopProduct findProductById(Integer pId);

    void decreaseProductStock(Integer pid, Integer descreasenNumber);
}
