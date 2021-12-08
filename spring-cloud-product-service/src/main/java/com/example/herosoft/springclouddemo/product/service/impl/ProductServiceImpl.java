package com.example.herosoft.springclouddemo.product.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.product.dao.ProductDao;
import com.example.herosoft.springclouddemo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ShopProduct> findProductByName(String pname) {

        return productDao.findProductByName(pname);
    }

    @Override
    public ShopProduct findProductById(Integer pId) {
        return productDao.findProductById(pId);
    }

    @Override
    public void decreaseProductStock(Integer pid, Integer descreasenNumber) {
        productDao.descreaseProductStock(pid,descreasenNumber);
    }
}
