package com.jsrf.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserLogConsumer {
    @KafkaListener(topics = {"topic.test"})
    public void receiveMessage(String message){
        System.out.println("消费数据");
        System.out.println("message:" + message);
    }

    public static void main(String[] args) {
        Long a = 5L;
        Integer b = 5;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));
    }
}
