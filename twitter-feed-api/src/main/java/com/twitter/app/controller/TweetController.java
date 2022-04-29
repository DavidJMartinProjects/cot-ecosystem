package com.twitter.app.controller;

import java.time.Duration;
import java.util.List;

import com.twitter.app.message.DestinationInfo;
import com.twitter.app.message.DestinationsConfig;
import com.twitter.app.message.MessageListenerContainerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.twitter.app.model.dto.TweetDto;
import com.twitter.app.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author davidjmartin
 */
@RestController
@RequestMapping(TweetController.TWEET_BASE_PATH)
@Slf4j
public class TweetController {

    public static final String TWEET_BASE_PATH = "/tweets";

    @Autowired
    private TweetService tweetService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private MessageListenerContainerFactory messageListenerContainerFactory;

    @Autowired
    private DestinationsConfig destinationsConfig;

    @GetMapping(
            value = "/queue/{name}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> receiveMessagesFromQueue(@PathVariable String name) {
        System.out.println("here.");
        DestinationInfo d = destinationsConfig
                .getQueues()
                .get(name);
        if (d == null) {
            return Flux.just(ResponseEntity.notFound()
                    .build());
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

        return Flux.interval(Duration.ofSeconds(5))
                .map(v -> "No news is good news")
                .mergeWith(f);
    }

}
