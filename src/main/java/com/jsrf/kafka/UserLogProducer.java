package com.jsrf.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class UserLogProducer {
    @Autowired
    KafkaTemplate  kafkaTemplate;

    public void sendLog() {
        System.err.println("生产消息:" );

        Integer [] bytes = new Integer[1024 * 1024];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 1;
        }
        String string = bytes.toString();
        ListenableFuture send = kafkaTemplate.send("topic.test", string);
        System.out.println(send);
    }
}
