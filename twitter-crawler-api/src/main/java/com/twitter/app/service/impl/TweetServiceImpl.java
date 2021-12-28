package com.twitter.app.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.app.model.dto.TweetDto;
import com.twitter.app.model.entity.TweetEntity;
import com.twitter.app.model.mapper.TweetMapper;
import com.twitter.app.repository.TweetRepository;
import com.twitter.app.service.TweetService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class TweetServiceImpl implements TweetService {

    private final TweetMapper tweetMapper;
    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetMapper tweetMapper, TweetRepository tweetRepository) {
        this.tweetMapper = tweetMapper;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<TweetDto> getTweets() {
        return tweetRepository.findAll()
            .stream()
            .map(tweetMapper::toDto)
            .sorted(Comparator.comparing(TweetDto::getId).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public TweetDto saveTweet(TweetDto tweetDto) {
        TweetEntity tweet = tweetRepository.save(tweetMapper.toEntity(tweetDto));
        log.debug("saved tweet");
        return tweetMapper.toDto(tweet);
    }

}
