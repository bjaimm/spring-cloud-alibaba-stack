package com.example.herosoft.springclouddemo.security.service;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="spring-cloud-user-service")
@RequestMapping(value="/user")
public interface UserService {

    @RequestMapping(value="/name/{username}")
    ShopUser findUserByName(@PathVariable("username") String username);

    @RequestMapping(value="/roles/{userid}")
    List<Role> findRolesByUserId(@PathVariable("userid") Integer userid);
}
