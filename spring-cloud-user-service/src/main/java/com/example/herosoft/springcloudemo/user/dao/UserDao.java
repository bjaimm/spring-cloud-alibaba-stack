package com.example.herosoft.springcloudemo.user.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<ShopUser,Integer> {
    @Query(value="select * from shop_user where user_name like %?1",nativeQuery = true)
    List<ShopUser> findUserByName(String username);
}
