package com.jsrf.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserLogConsumer {
    @KafkaListener(topics = {"first"})
    public void receiveMessage(String message){
        System.out.println("消费数据");
        System.out.println("message:" + message);
    }
}
