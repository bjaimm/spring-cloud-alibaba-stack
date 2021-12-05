package com.example.herosoft.springclouddemo.product.controller;

import com.example.herosoft.springclouddemo.product.messages.CustomChannels;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding({CustomChannels.class,Sink.class})
public class TestRocketMQ {

    private CustomChannels customChannels;

    public TestRocketMQ(CustomChannels customChannels){
        this.customChannels=customChannels;
    }

    @PostMapping("/send-RocketMQ")
    public String sendMessage(){
        Message<String> message1 = MessageBuilder.withPayload("my message")
                .setHeader(RocketMQHeaders.TAGS,"tag3")
                .setHeader("mytag","my-tag")
                .build();
        customChannels.output1().send(message1);

        Message<String> message2 = MessageBuilder.withPayload("your message")
                .setHeader(RocketMQHeaders.TAGS,"tag2")
                .setHeader("mytag","your-tag")
                .build();

        customChannels.output1().send(message2);
        return "Send messages successfully!";
    }

    @StreamListener(value=CustomChannels.INPUT1,condition = "headers['mytag']=='my-tag'")
    public void reciveMessage1(Message message){
        System.out.println("TAGS:"+message.getHeaders().get("rocketmq_TAGS")+"   Payload:"+message.getPayload().toString());
    }

    @StreamListener(value=CustomChannels.INPUT1,condition = "headers['mytag']=='your-tag'")
    public void reciveMessage2(Message message){
        System.out.println("TAGS:"+message.getHeaders().get("rocketmq_TAGS")+"   Payload:"+message.getPayload().toString());
    }

}
