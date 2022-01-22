package cot.swap.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
