package com.jsrf.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class UserLogConsumer {
    @KafkaListener(topics = {"topic.test"})
    public void receiveMessage(String message){
        System.out.println("消费数据");
        System.out.println("message:" + message);
    }

    public static void main(String[] args) {
        String str = "4.15";
        BigDecimal bigDecimal = new BigDecimal(str);
        bigDecimal = bigDecimal.multiply(new BigDecimal(4)).setScale(0, RoundingMode.HALF_UP);
        System.out.println(bigDecimal.intValue());
    }
}
