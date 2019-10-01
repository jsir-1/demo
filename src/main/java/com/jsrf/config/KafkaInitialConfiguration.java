package com.jsrf.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author jsrf
 */
@Component
public class KafkaInitialConfiguration {
    /**
     * 创建TopicName为topic.test的Topic并设置分区数为8以及副本数为1
     *
     * @return
     */
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("topic.test", 8, (short) 3);
    }
}
