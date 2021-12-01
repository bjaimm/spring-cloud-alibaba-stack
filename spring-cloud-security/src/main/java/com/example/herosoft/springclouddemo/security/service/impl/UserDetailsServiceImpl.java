package com.example.herosoft.springclouddemo.security.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springclouddemo.security.domain.dto.CustomUserDetails;
import com.example.herosoft.springclouddemo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里根据用户名，查询数据库，获取对应的用户
        //ShopUser myUser=userMapper.findByUsername(username);
        ShopUser myUser = userService.findUserByName(username);

        if(null==myUser){
            throw new UsernameNotFoundException("用户不存在");
        }

        //这里可以从userMapper调用findRoleByUserId获取用户权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roles = userService.findRolesByUserId(myUser.getUId());

        for(Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new CustomUserDetails(myUser.getUserName(),myUser.getUserPassword(),"Dept10",authorityList);
    }

}
