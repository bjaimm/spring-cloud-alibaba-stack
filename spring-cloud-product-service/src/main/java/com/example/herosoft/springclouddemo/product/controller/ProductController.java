package com.example.herosoft.springclouddemo.product.controller;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, you are in Product Service now";
    }

    @RequestMapping("/name/{pname}")
    public List<ShopProduct> findProductByName(@PathVariable("pname") String pname){
        return productService.findProductByName(pname);
    }
}
