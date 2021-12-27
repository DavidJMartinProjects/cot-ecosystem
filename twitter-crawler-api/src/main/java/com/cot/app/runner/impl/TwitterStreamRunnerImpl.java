package com.cot.app.runner.impl;

import com.cot.app.config.TwitterKeywordsConfig;
import com.cot.app.listener.TwitterStatusListener;
import com.cot.app.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
@Slf4j
public class TwitterStreamRunnerImpl implements StreamRunner {

    @Autowired
    private TwitterKeywordsConfig twitterKeywordsConfig;

    @Autowired
    private TwitterStatusListener twitterStatusListener;

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
        twitterStream.addListener(twitterStatusListener);
        addKeywordfilter();
    }

    private void addKeywordfilter() {
        String[] keywords = twitterKeywordsConfig.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        log.info("Started filtering twitter stream for keywords: {}", Arrays.toString(keywords));
    }
}
