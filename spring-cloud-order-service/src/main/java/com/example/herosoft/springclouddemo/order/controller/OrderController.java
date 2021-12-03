package com.example.herosoft.springclouddemo.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.herosoft.springclouddemo.common.domain.POJO.TxMessage;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopProduct;
import com.example.herosoft.springclouddemo.common.domain.entity.ShopUser;
import com.example.herosoft.springclouddemo.order.service.ProductService;
import com.example.herosoft.springclouddemo.order.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, you are in Order Service now!";
    }

    @RequestMapping("/user/{username}")
    public ShopUser findUserByName(@PathVariable String username){
        log.info("Open Feign call starting for spring-cloud-user-service!");
        return userService.findUserByName(username);
    }

    @RequestMapping("/product/{pname}")
    public List<ShopProduct> findProductByName(@PathVariable String pname){
        log.info("Open Feign call starting for spring-cloud-product-service");
        return productService.findProductByName(pname);
    }

    @RequestMapping("/placeOrder")
    public String placeOrder(){
        return "Success";
    }

    public void submitOrder(Integer productId, Integer orderCount){
        String txId = UUID.randomUUID().toString();
        TxMessage txMessage = new TxMessage(productId,orderCount,txId);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("txMessage",txMessage);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();

        //发送一条事务消息
        rocketMQTemplate.sendMessageInTransaction("tx-order-group","topic-txmsg",message,null);
    }
}
