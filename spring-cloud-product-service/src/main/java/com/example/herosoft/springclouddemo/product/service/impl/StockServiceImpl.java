package com.example.herosoft.springclouddemo.product.service.impl;

import com.example.herosoft.springclouddemo.common.domain.model.TxMessage;
import com.example.herosoft.springclouddemo.product.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockServiceImpl implements StockService {
    @Override
    public void decreaseStock(TxMessage txMessage) {
        log.info("商品微服务执行本地商品库存调整:{}",txMessage);

    }
}
