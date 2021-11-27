package com.example.herosoft.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name="shop_order")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oId;

    private Integer uId;
    private String shopUserName;

    private Integer pId;
    private String shopProductName;
    private Double shopProductPrice;

    private Integer orderNumber;
}
