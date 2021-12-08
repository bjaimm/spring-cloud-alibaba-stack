package com.example.herosoft.springclouddemo.order.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.TxOrderMessage;
import com.example.herosoft.springclouddemo.order.dao.TxOrderMessageDao;
import com.example.herosoft.springclouddemo.order.service.TxOrderMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TxOrderMessageServiceImpl implements TxOrderMessageService {

    @Autowired
    private TxOrderMessageDao txOrderMessageDao;

    @Override
    public void save(TxOrderMessage txOrderMessage) {
        txOrderMessageDao.save(txOrderMessage);
    }

    @Override
    public TxOrderMessage findTxOrderMessagesByTxId(String txId) {
        return txOrderMessageDao.findTxOrderMessagesByTxId(txId);
    }
}
