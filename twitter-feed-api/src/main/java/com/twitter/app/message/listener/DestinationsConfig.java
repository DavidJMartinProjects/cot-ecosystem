package com.twitter.app.message.listener;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("destinations")
public class DestinationsConfig {
    public static final String TWEET_QUEUE = "tweetQueue";
    private Map<String, DestinationInfo> queues = new HashMap<>();

    private Map<String, DestinationInfo> topics = new HashMap<>();

    @PostConstruct
    public void config() {
        queues.put(TWEET_QUEUE, new DestinationInfo(TWEET_QUEUE, TWEET_QUEUE));
    }

    public Map<String, DestinationInfo> getQueues() {
        return queues;
    }

    public void setQueues(Map<String, DestinationInfo> queues) {
        this.queues = queues;
    }

    public Map<String, DestinationInfo> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, DestinationInfo> topics) {
        this.topics = topics;
    }

}