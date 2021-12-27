package com.cot.app.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TWEETS_TOPIC = "tweets";

    @Bean
    public NewTopic topicExample() {
        return TopicBuilder.name(TWEETS_TOPIC)
            .partitions(3)
            .replicas(3)
            .build();
    }

}
