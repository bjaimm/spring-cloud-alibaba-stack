package com.example.herosoft.springclouddemo.order.controller;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springclouddemo.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, you are in Order Service now!";
    }

    @RequestMapping("/user/{username}")
    public ShopUser findUserByName(@PathVariable String username){
        return userService.findUserByName(username);
    }
}
