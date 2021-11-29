package com.example.herosoft.springclouddemo.product.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<ShopProduct,Integer> {

    @Query(value="select * from shop_product where product_name like %?1",nativeQuery = true)
    List<ShopProduct> findProductByName(String pname);
}
