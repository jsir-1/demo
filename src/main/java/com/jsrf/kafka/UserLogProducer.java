package com.jsrf.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class UserLogProducer {
    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendLog() {
        System.err.println("生产消息:" );
        ListenableFuture send = kafkaTemplate.send("topic.test", "data test");
        System.out.println(send.completable());
    }
}
