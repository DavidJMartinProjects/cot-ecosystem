package com.twitter.app.controller;

import com.twitter.app.twitter.runner.RecentTweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(RecentTweetsController.API_TWEETS_RECENT_PATH)
@CrossOrigin
public class RecentTweetsController {

    public static final String API_TWEETS_RECENT_PATH = "/api/tweets/recent";

    @Autowired
    private RecentTweetsService recentTweetsService;

    @GetMapping
    @CrossOrigin
    public Flux<?> getRecentTweets() {
        return Flux.fromIterable(recentTweetsService.getRecentTweets());
    }

}
