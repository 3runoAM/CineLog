package com.brunoam.CineLog.config;

import org.springframework.context.annotation.Configuration;
import com.brunoam.CineLog.utils.ImageValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {
    @Bean
    public ImageValidator profilePicValidator() {
        return new ImageValidator(4096, 4096, 5 * 1024 * 1024);
    }

    @Bean
    public ImageValidator moviePosterValidator() {
        return new ImageValidator(8192, 8192, 10 * 1024 * 1024);
    }
}