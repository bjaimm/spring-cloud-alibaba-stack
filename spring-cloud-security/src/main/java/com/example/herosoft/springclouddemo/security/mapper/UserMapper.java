package com.example.herosoft.springclouddemo.security.mapper;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    @Select("select u_id,user_name,user_password from shop_user where user_name=#{username}")
    ShopUser findByUsername(String username);

    @Insert("insert into shop_user(user_name,password) values(#{user_name},#{password})")
    int save(ShopUser user);

    @Select("select role.id,role.name,role.description from user_role join role on user_role.role_id= role.id where user_role.user_id=#{userId}")
    List<Role> findRoleByUserId(Integer  userId);
}
