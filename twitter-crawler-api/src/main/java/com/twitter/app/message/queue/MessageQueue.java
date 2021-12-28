package com.twitter.app.message.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author davidjmartin
 */
@Configuration
public class MessageQueue {

    @Value("${twitter-listener-service.event.queue.name}")
    private String queueName;

    @Bean
    public Queue myQueue() {
        return new Queue(queueName, false);
    }

}
