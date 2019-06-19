package com.jsrf.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@EnableScheduling //这里是为了测试加入定时调度
public class UserLogProducer {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "00/10 * * * * ?")
    public void sendLog() {
        System.err.println("生成数据:" );
        ListenableFuture send = kafkaTemplate.send("first", "data test");
        System.out.println(send.completable());
    }
}
