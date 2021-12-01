package com.example.herosoft.springcloudemo.user.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<ShopUser,Integer> {
    @Query(value="select * from shop_user where user_name like %?1",nativeQuery = true)
    ShopUser findUserByName(String username);
}
