package com.twitter.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author davidjmartin
 */
@RestController
@RequestMapping(TweetController.TWEET_BASE_PATH)
public class TweetController {
    public static final String TWEET_BASE_PATH = "/api/tweets";
    public static final String QUEUE_PATH = "/queue/{name}";

    private static Logger log = LoggerFactory.getLogger(TweetController.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private DestinationConfig destinationConfig;

    @Autowired
    private MessageListenerContainerFactory messageListenerContainerFactory;

    @PostConstruct
    public void setupTopicDestinations() {
        // For topic each consumer will have its own Queue, so no binding
        destinationConfig.getTopics()
            .forEach((key, destination) -> {
                log.info("[I98] Creating TopicExchange: name={}, exchange={}", key, destination.getExchange());
                Exchange ex = ExchangeBuilder.topicExchange(destination.getExchange())
                    .durable(true)
                    .build();
                amqpAdmin.declareExchange(ex);
                log.info("[I107] Topic Exchange successfully created.");
            });
    }

    @CrossOrigin
    @GetMapping(value = QUEUE_PATH, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> receiveMessagesFromTopic(@PathVariable String name) {
        log.info("GET: /{}", QUEUE_PATH);
        DestinationConfig.DestinationInfo topicName = destinationConfig.getTopics().get(name);

        if (topicName == null) {
            return Flux.just(ResponseEntity.notFound()
                    .build());
        }

        Queue topicQueue = createTopicQueue(topicName);
        String qname = topicQueue.getName();
        MessageListenerContainer listenerContainer = messageListenerContainerFactory.createMessageListenerContainer(qname);

        Flux<String> response = Flux.<String>create(emitter -> {
            log.info("[I168] Adding listener, queue={}", qname);
            listenerContainer.setupMessageListener((MessageListener) m -> {
                log.info("[I137] Message received, queue={}", qname);
                if (emitter.isCancelled()) {
                    log.info("[I166] cancelled, queue={}", qname);
                    listenerContainer.stop();
                    return;
                }
                String payload = new String(m.getBody());
                emitter.next(payload);
                log.info("[I176] Message sent to client, queue={}", qname);
            });
            emitter.onRequest(v -> {
                log.info("[I171] Starting container, queue={}", qname);
                listenerContainer.start();
            });
            emitter.onDispose(() -> {
                log.info("[I176] onDispose: queue={}", qname);
                amqpAdmin.deleteQueue(qname);
                listenerContainer.stop();
            });
            log.info("[I171] Container started, queue={}", qname);

        });
        return response;
    }

    private Queue createTopicQueue(DestinationConfig.DestinationInfo destination) {

        Exchange ex = ExchangeBuilder.topicExchange(destination.getExchange())
                .durable(true)
                .build();

        amqpAdmin.declareExchange(ex);

        Queue q = QueueBuilder.nonDurable()
                .build();

        amqpAdmin.declareQueue(q);

        Binding b = BindingBuilder.bind(q)
                .to(ex)
                .with(destination.getRoutingKey())
                .noargs();

        amqpAdmin.declareBinding(b);

        return q;
    }


}
