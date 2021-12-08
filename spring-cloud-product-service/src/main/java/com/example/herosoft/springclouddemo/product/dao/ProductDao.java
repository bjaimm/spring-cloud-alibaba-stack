package com.example.herosoft.springclouddemo.product.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<ShopProduct,Integer> {

    @Query(value="select * from shop_product where product_name like %?1",nativeQuery = true)
    List<ShopProduct> findProductByName(String pname);

    @Query(value="select * from shop_product where id = ?1",nativeQuery = true)
    ShopProduct findProductById(Integer pId);

    @Transactional
    @Modifying
    @Query(value="update shop_product set product_stock=product_stock - ?2 where id=?1 ",nativeQuery = true)
    void descreaseProductStock(Integer pid, Integer descreasenNumber);
}
