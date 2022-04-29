package com.twitter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.app.model.dto.TweetDto;
import com.twitter.app.service.TweetService;
import lombok.extern.slf4j.Slf4j;

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

    @GetMapping
    public ResponseEntity<List<TweetDto>> getTweets() {
        log.info("GET: {}", TWEET_BASE_PATH);
        return ResponseEntity.ok().body(tweetService.getTweets());
    }

}
