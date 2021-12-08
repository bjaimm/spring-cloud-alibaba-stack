package com.example.herosoft.springclouddemo.product.messages;

import com.alibaba.fastjson.JSONObject;
import com.example.herosoft.springclouddemo.common.domain.model.TxMessage;
import com.example.herosoft.springclouddemo.product.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/*RocketMQMessageListener注解版本 - 未调通
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup ="tx-order-consumer-group",topic = "topic-txmsg")
public class StockTxMessageConsumer implements RocketMQListener<Message> {

    @Autowired
    private StockService stockService;

    @Override
    public void onMessage(Message message) {
        TxMessage txMessage = this.getTxMessage(message);

        log.info("商品微服务开始消费消息{}",txMessage);

        stockService.decreaseStock(txMessage);

    }

    private TxMessage getTxMessage(Message message) {
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String txStr = jsonObject.getString("txMessage");
        return JSONObject.parseObject(txStr,TxMessage.class);
    }
}
*/

@EnableBinding(Sink.class)
@Component
@Slf4j
public class StockTxMessageConsumer {
    @Autowired
    private StockService stockService;

    @StreamListener(value=Sink.INPUT)
    public void onMessage(Message message) {
        TxMessage txMessage = this.getTxMessage(message);

        log.info("商品微服务开始消费消息{}",txMessage);

        stockService.decreaseStock(txMessage);

        log.info("商品微服务完成消费消息{}",txMessage);

    }

    private TxMessage getTxMessage(Message message) {
        String messageString = message.getPayload().toString();
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String txStr = jsonObject.getString("txMessage");
        return JSONObject.parseObject(txStr,TxMessage.class);
    }

}