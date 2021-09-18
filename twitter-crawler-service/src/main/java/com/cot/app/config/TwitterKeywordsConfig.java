package com.cot.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "twitter-crawler-service")
@Data
public class TwitterKeywordsConfig  {
    private List<String> twitterKeywords;
}
