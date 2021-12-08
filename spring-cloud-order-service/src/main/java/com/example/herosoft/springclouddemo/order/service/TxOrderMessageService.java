package com.example.herosoft.springclouddemo.order.service;

import com.example.herosoft.springclouddemo.common.domain.entity.TxOrderMessage;
import org.springframework.stereotype.Service;

@Service
public interface TxOrderMessageService {
    void save(TxOrderMessage txOrderMessage);
    TxOrderMessage findTxOrderMessagesByTxId(String txId);
}
