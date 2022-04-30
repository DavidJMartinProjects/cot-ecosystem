package com.twitter.app.message.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerFactory {

    @Autowired
    private ConnectionFactory connectionFactory;

    public MessageListenerFactory() {
    }

    public MessageListenerContainer createListener(String queueName) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer(connectionFactory);
        listener.addQueueNames(queueName);
        listener.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return listener;
    }

}