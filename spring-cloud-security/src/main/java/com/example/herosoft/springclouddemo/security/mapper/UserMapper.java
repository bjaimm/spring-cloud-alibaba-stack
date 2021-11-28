package com.example.herosoft.springclouddemo.security.mapper;

import com.example.herosoft.springclouddemo.security.domain.entity.MyRole;
import com.example.herosoft.springclouddemo.security.domain.entity.MyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    @Select("select u_id,user_name,user_password from shop_user where user_name=#{username}")
    MyUser findByUsername(String username);

    @Insert("insert into shop_user(user_name,password) values(#{user_name},#{password})")
    int save(MyUser user);

    @Select("select role.name from user_role join role on user_role.role_id= role.id where user_role.user_id=#{userId}")
    List<MyRole> findRoleByUserId(Integer  userId);
}
