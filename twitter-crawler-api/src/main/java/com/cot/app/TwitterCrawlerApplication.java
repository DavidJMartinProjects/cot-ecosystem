package com.cot.app;

import com.cot.app.config.TwitterKeywordsConfig;
import com.cot.app.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class TwitterCrawlerApplication implements CommandLineRunner {

    @Autowired
    TwitterKeywordsConfig twitterKeywordsConfig;

    @Autowired
    StreamRunner streamRunner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterCrawlerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Twitter query keywords: " + Arrays.toString(twitterKeywordsConfig.getTwitterKeywords().toArray(new String[0])));
        streamRunner.start();
    }

}
