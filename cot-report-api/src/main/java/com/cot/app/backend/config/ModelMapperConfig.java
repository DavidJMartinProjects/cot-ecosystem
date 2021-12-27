package com.cot.app.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.modelmapper.ModelMapper;

/**
 * @author DavidJMartin
 */
@Configuration
public class ModelMapperConfig {
    
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return modelMapper;
    }

}
