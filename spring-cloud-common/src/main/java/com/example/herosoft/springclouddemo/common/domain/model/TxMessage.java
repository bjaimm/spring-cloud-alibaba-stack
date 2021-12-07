package com.example.herosoft.springclouddemo.common.domain.model;

import lombok.Data;

@Data
public class TxMessage {
    public TxMessage(Integer productId, Integer orderCount, String txId,Integer userId) {
        this.productId = productId;
        this.orderCount = orderCount;
        this.txId = txId;
        this.userId = userId;
    }
    private Integer userId;
    private Integer productId;
    private Integer orderCount;
    private String txId;

}
