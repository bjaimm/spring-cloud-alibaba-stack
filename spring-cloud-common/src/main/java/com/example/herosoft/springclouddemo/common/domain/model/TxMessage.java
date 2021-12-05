package com.example.herosoft.springclouddemo.common.domain.model;

import lombok.Data;

@Data
public class TxMessage {
    public TxMessage(Integer productId, Integer orderCount, String txId) {
        this.productId = productId;
        this.orderCount = orderCount;
        this.txId = txId;
    }

    private Integer productId;
    private Integer orderCount;
    private String txId;

}
