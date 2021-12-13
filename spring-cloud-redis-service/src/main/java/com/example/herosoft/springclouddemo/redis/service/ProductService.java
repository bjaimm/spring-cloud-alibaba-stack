package com.example.herosoft.springclouddemo.redis.service;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="spring-cloud-product-service")
@RequestMapping("/product")
public interface ProductService {

    @RequestMapping("/name/{pname}")
    List<ShopProduct> findProductByName(@PathVariable("pname") String pname);

    @RequestMapping("/id/{pid}")
    ShopProduct findProductById(@PathVariable("pid") Integer pid);
}
