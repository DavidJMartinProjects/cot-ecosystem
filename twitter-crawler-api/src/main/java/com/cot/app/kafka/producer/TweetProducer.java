package com.cot.app.kafka.producer;

import com.cot.app.models.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.cot.app.kafka.config.KafkaTopicConfig.TWEETS_TOPIC;

@Component
@Slf4j
public class TweetProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Tweet tweet) {
        log.info(String.format("#### -> Producing message -> %s", tweet));
        this.kafkaTemplate.send(TWEETS_TOPIC, String.valueOf(tweet));
    }

}
