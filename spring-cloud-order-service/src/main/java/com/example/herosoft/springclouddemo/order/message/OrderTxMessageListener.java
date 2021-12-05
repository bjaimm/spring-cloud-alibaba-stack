package com.example.herosoft.springclouddemo.order.message;

import com.alibaba.fastjson.JSONObject;
import com.example.herosoft.springclouddemo.common.domain.model.TxMessage;
import com.example.herosoft.springclouddemo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "tx-order-group")
public class OrderTxMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        try{
            log.info("订单微服务开始执行本地事务。。。。。。");
            TxMessage txMessage = this.getTxMessage(message);
            log.info("ProductID:"+txMessage.getProductId()+" OrderCount:"+txMessage.getOrderCount()+" TxId:"+txMessage.getTxId());

            //执行本地事务
            orderService.submitOrderAndSaveTxId(txMessage);

            log.info("订单微服务提交事务");
            return RocketMQLocalTransactionState.COMMIT;
            //log.info("订单微服务返回事务Unknown状态");
            //return RocketMQLocalTransactionState.UNKNOWN;
        }
        catch(Exception e){
            e.printStackTrace();
            log.info("订单微服务回滚事务");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    private TxMessage getTxMessage(Message message) {
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String txStr = jsonObject.getString("txMessage");
        return JSONObject.parseObject(txStr,TxMessage.class);
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("订单微服务查询本地事务状态");
        TxMessage txMessage = this.getTxMessage(message);

        //这里调用订单微服务查询本地事务的状态，如果已完成，则返回RocketMQLocalTransactionState.COMMIT,
        //否则返回RocketMQLocalTransactionState.ROLLBACK

        return RocketMQLocalTransactionState.COMMIT;
    }
}
