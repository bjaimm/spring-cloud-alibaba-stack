package com.example.herosoft.springclouddemo.product.service;

import com.example.herosoft.springclouddemo.common.domain.model.TxMessage;
import org.springframework.stereotype.Service;

@Service
public interface StockService {
    public void decreaseStock(TxMessage txMessage);
}
