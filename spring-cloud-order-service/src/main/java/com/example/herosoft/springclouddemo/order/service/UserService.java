package com.example.herosoft.springclouddemo.order.service;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="spring-cloud-user-service")
@RequestMapping("/user")
public interface UserService {
    @RequestMapping("/name/{username}")
    List<ShopUser> findUserByName(@PathVariable("username") String username);
}
