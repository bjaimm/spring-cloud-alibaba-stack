package com.example.herosoft.springclouddemo.product.messages;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
    String OUTPUT1="output1";
    String INPUT1 = "input1";

    @Output(CustomChannels.OUTPUT1)
    MessageChannel output1();

    @Input(CustomChannels.INPUT1)
    SubscribableChannel input1();
}
