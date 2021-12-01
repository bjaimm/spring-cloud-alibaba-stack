package com.example.herosoft.springcloudemo.user.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Integer> {

    @Query(value="select role.id,role.name,role.description from user_role join role on user_role.role_id= role.id where user_role.user_id= ?1",nativeQuery = true)
    List<Role> findRolesByUserId(Integer  userId);
}
