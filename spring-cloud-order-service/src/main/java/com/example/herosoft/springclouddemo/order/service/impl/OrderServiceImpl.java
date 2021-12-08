package com.example.herosoft.springclouddemo.order.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.ShopOrder;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springclouddemo.common.domain.entity.TxOrderMessage;
import com.example.herosoft.springclouddemo.common.domain.model.TxMessage;
import com.example.herosoft.springclouddemo.order.dao.OrderDao;
import com.example.herosoft.springclouddemo.order.service.OrderService;
import com.example.herosoft.springclouddemo.order.service.ProductService;
import com.example.herosoft.springclouddemo.order.service.TxOrderMessageService;
import com.example.herosoft.springclouddemo.order.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TxOrderMessageService txOrderMessageService;

    @Override
    public void submitOrderAndSaveTxId(TxMessage txMessage) {
        ShopOrder shopOrder = new ShopOrder();
        TxOrderMessage txOrderMessage = new TxOrderMessage();

        ShopUser user = userService.findUserById(txMessage.getUserId());
        ShopProduct product = productService.findProductById(txMessage.getProductId());

        shopOrder.setUId(txMessage.getUserId());
        shopOrder.setShopUserName(user.getUserName());
        shopOrder.setPId(txMessage.getProductId());
        shopOrder.setShopProductName(product.getProductName());
        shopOrder.setShopProductPrice(product.getProductPrice());
        shopOrder.setOrderNumber(txMessage.getOrderCount());
        shopOrder.setTxId(txMessage.getTxId());

        orderDao.save(shopOrder);

        txOrderMessage.setOrderId(shopOrder.getOId());
        txOrderMessage.setTxId(txMessage.getTxId());

        txOrderMessageService.save(txOrderMessage);
        log.info("商品订单保存成功，订单流水ID:"+shopOrder.getOId());
    }
}
