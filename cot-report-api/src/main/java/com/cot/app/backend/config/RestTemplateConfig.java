package com.cot.app.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author DavidJMartin
 */
@Configuration
public class RestTemplateConfig {

    private final RestTemplate restTemplate = new RestTemplate();

    @Bean
    public RestTemplate restTemplate() {
        return restTemplate;
    }

}
