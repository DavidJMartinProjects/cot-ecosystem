package com.twitter.app.twitter.listener;

import com.twitter.app.message.producer.MessageProducer;
import com.twitter.app.model.dto.TweetDto;
import com.twitter.app.service.impl.TweetServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.StatusAdapter;

/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class TweetListener extends StatusAdapter {

    @Autowired
    private TweetServiceImpl tweetService;

    @Autowired
    private MessageProducer messageProducer;

    @Override
    public void onStatus(Status status) {
        TweetDto tweet = TweetDto.builder()
                .createdAt(String.valueOf(status.getCreatedAt().getTime()))
                .user(status.getUser().getScreenName())
                .text(status.getText())
                .place(status.getPlace().getCountry())
                .build();
                
        log.info("[x] received tweet");
        
        try {
            tweetService.saveTweet(tweet);  
            messageProducer.sendMessage(tweet);
        } catch (Exception exception) {
            log.debug("encountered exception handling tweet: {}", exception.getMessage(), exception);
        }
    }

}
