package com.example.herosoft.springclouddemo.order.service;

import com.example.herosoft.springclouddemo.common.domain.model.TxMessage;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void submitOrderAndSaveTxId(TxMessage txMessage);
}
