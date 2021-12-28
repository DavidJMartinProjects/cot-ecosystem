package com.twitter.app.twitter.runner;

import com.twitter.app.config.TweetKeywordsConfig;
import com.twitter.app.twitter.listener.TweetListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class TweetStreamRunnerImpl implements StreamRunner {

    @Autowired
    private TweetKeywordsConfig tweetKeywordsConfig;

    @Autowired
    private TweetListener tweetListener;

    private TwitterStream twitterStream;

    @PreDestroy
    public void shutdown() {
        if(!ObjectUtils.isEmpty(twitterStream)) {
            log.info("closing twitter stream.");
            twitterStream.shutdown();
        }
    }

    @Override
    public void start() {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(tweetListener);
        addKeywordFilter(twitterStream);
    }

    private void addKeywordFilter(TwitterStream twitterStream) {
        String[] keywords = tweetKeywordsConfig.getTweetKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        this.twitterStream.filter(filterQuery);
        log.info("filtering tweets with keywords: {}", Arrays.toString(keywords));
    }

}
