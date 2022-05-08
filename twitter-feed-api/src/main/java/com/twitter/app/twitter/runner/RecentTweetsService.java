package com.twitter.app.twitter.runner;

import com.twitter.app.config.TweetKeywordsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RecentTweetsService {
    @Autowired
    private TweetKeywordsConfig tweetKeywordsConfig;
    private final List<Status> recentTweets = new ArrayList<>();
    private final Twitter twitter = new TwitterFactory().getInstance();
    private String[] keywords = new String[]{};

    @PostConstruct
    public void init() {
        keywords = tweetKeywordsConfig.getTweetKeywords().toArray(new String[0]);
    }

    public void fetchRecentTweets() {
        log.info("Retrieving recent tweets...");
        Arrays.stream(keywords)
            .forEach(username -> fetchRecentTweetsForUsername(recentTweets, username));
    }
    private void fetchRecentTweetsForUsername(List<Status> recentTweets, String username) {
        try {
            log.info("Retrieving timeline for username: {}", username);
            recentTweets.addAll(twitter.getUserTimeline(username));
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Status> getRecentTweets() {
        return recentTweets;
    }

}
