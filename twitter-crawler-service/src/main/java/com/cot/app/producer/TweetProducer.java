package com.cot.app.producer;

import com.cot.app.models.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TweetProducer {

    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Tweet tweet) {
        log.info(String.format("#### -> Producing message -> %s", tweet));
        this.kafkaTemplate.send(TOPIC, String.valueOf(tweet));
    }

}
