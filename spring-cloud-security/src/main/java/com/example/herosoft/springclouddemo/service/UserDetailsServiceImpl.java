package com.example.herosoft.springclouddemo.service;

import com.example.herosoft.springclouddemo.domain.entity.MyRole;
import com.example.herosoft.springclouddemo.domain.entity.MyUser;
import com.example.herosoft.springclouddemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里根据用户名，查询数据库，获取对应的用户
        MyUser myUser=userMapper.findByUsername(username);

        if(null==myUser){
            throw new UsernameNotFoundException("用户不存在");
        }

        //这里可以从userMapper调用findRoleByUserId获取用户权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<MyRole> roles = userMapper.findRoleByUserId(myUser.getId());

        for(MyRole role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(myUser.getUser_name(),myUser.getPassword(),authorityList);
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
