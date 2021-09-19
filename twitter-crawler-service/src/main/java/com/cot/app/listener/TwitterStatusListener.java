package com.cot.app.listener;

import com.cot.app.models.Tweet;
import com.cot.app.producer.TweetProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
@Slf4j
public class TwitterStatusListener extends StatusAdapter {

    @Autowired
    TweetProducer tweetProducer;

    @Override
    public void onStatus(Status status) {
        Tweet tweet = Tweet.builder()
            .createdAt(String.valueOf(status.getCreatedAt().getTime()))
            .user(status.getUser().getScreenName())
            .text(status.getText())
            .place(status.getPlace().getCountry())
            .build();

        log.info("latest tweet: {}", tweet);
        tweetProducer.sendMessage(tweet);

    }

}
