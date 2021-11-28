package com.example.herosoft.springclouddemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello, you are in Order Service now";
    }
}
