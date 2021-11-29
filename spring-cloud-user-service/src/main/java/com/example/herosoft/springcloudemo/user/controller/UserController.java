package com.example.herosoft.springcloudemo.user.controller;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springcloudemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(@RequestHeader("LoginAuth") String loginAuth){

        return "Hello, you are in User Service now and the login authentication info:"+loginAuth;
    }

    @RequestMapping("/name/{username}")
    public List<ShopUser> findUserByName(@PathVariable("username") String username){
        return userService.findUserByName(username);
    }
}
