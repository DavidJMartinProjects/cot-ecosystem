package com.twitter.app.message.producer;

import com.twitter.app.model.dto.TweetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.JSONObject;

/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class MessageProducer {

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMessage(Object tweet) {
        try {
            this.template.convertAndSend("tweetQueue", "tweetQueue", new JSONObject(tweet.toString()).toString());
            log.info("[x] Sent tweet to queue.");
        } catch (Exception exception) {
            log.error("unable to send message to topic. {}", exception.getMessage());
        }
    }

}
