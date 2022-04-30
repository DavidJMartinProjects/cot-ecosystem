package com.twitter.app.controller;

import com.twitter.app.message.listener.DestinationInfo;
import com.twitter.app.message.listener.DestinationsConfig;
import com.twitter.app.message.listener.MessageListenerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
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
    private MessageListenerFactory messageListenerFactory;

    @Autowired
    private DestinationsConfig destinationsConfig;

    @GetMapping(value = QUEUE_PATH, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin
    public Flux<?> receiveMessagesFromQueue(@PathVariable String name) {
        log.info("GET: {}", QUEUE_PATH);

        DestinationInfo d = destinationsConfig.getQueues().get(name);
        if (ObjectUtils.isEmpty(d)) {
            log.info("Encountered error: the specified topic was not found");
            return Flux.just(
                ResponseEntity.notFound().build()
            );
        }

        MessageListenerContainer listener = messageListenerFactory.createListener(d.getRouteKey());
        Flux<String> response = Flux.<String> create(emitter -> {
            listener.setupMessageListener((MessageListener) m -> {
                String payload = new String(m.getBody());
                emitter.next(payload);
            });
            emitter.onRequest(v -> {
                listener.start();
            });
            emitter.onDispose(listener::stop);
        });
        return response;
    }

}
