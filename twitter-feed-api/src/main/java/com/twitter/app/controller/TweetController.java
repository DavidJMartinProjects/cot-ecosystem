package com.twitter.app.controller;

import com.twitter.app.message.DestinationInfo;
import com.twitter.app.message.DestinationsConfig;
import com.twitter.app.message.MessageListenerContainerFactory;
import com.twitter.app.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author davidjmartin
 */
@RestController
@RequestMapping(TweetController.TWEET_BASE_PATH)
@Slf4j
public class TweetController {
    public static final String TWEET_BASE_PATH = "/tweets";
    public static final String QUEUE_PATH = "/queue/{name}";

    @Autowired
    private TweetService tweetService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private MessageListenerContainerFactory messageListenerContainerFactory;

    @Autowired
    private DestinationsConfig destinationsConfig;

    @GetMapping(value = QUEUE_PATH, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> receiveMessagesFromQueue(@PathVariable String name) {

        DestinationInfo d = destinationsConfig.getQueues().get(name);

        if (ObjectUtils.isEmpty(d)) {
            return Flux.just(ResponseEntity.notFound().build());
        }

        MessageListenerContainer mlc = messageListenerContainerFactory
            .createMessageListenerContainer(d.getRouteKey());

        Flux<String> f = Flux.<String> create(emitter -> {
            mlc.setupMessageListener((MessageListener) m -> {
                String payload = new String(m.getBody());
                emitter.next(payload);
            });

            emitter.onRequest(v -> {
                mlc.start();
            });

            emitter.onDispose(() -> {
                mlc.stop();
            });
        });

        return f;
    }

}
