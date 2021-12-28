package com.twitter.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author davidjmartin
 */
@Configuration
@ConfigurationProperties(prefix = "twitter-listener-service")
@Data
public class TweetKeywordsConfig {
    private List<String> tweetKeywords;
}
