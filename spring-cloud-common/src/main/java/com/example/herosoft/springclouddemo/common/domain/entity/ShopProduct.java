package com.example.herosoft.springclouddemo.common.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Data
@Entity(name="shop_product")
public class ShopProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private Double productPrice;
    private Integer productStock;
}

