package com.twitter.app.message.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twitter.app.model.dto.TweetDto;
import lombok.extern.slf4j.Slf4j;

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

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("user");
        connectionFactory.setPassword("BSwrxgfh0W");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMessage(TweetDto tweet) {
        try {
            this.template.convertAndSend(queue.getName(), tweet.toString());
            log.info("[x] Sent tweet to queue.");
        } catch (Exception exception) {
            log.error("unable to send message to topic.");
        }
    }

}
