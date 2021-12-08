package com.example.herosoft.springclouddemo.order.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.TxOrderMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TxOrderMessageDao extends JpaRepository<TxOrderMessage,Integer> {

    TxOrderMessage findTxOrderMessagesByTxId(String txId);
}
