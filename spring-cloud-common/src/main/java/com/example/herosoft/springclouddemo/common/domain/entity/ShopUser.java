package com.example.herosoft.springclouddemo.common.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name="shop_user")
public class ShopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY   )
    private Integer uId;
    private String userName;
    private String userPassword;
    private String userTelephone;
}
