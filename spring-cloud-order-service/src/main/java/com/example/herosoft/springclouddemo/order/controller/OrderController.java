package com.example.herosoft.springclouddemo.order.controller;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springclouddemo.order.service.ProductService;
import com.example.herosoft.springclouddemo.order.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, you are in Order Service now!";
    }

    @RequestMapping("/user/{username}")
    public ShopUser findUserByName(@PathVariable String username){
        log.info("Open Feign call starting for spring-cloud-user-service!");
        return userService.findUserByName(username);
    }

    @RequestMapping("/product/{pname}")
    public List<ShopProduct> findProductByName(@PathVariable String pname){
        log.info("Open Feign call starting for spring-cloud-product-service");
        return productService.findProductByName(pname);
    }
}
