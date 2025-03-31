package com.brunoam.CineLog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${tmdb.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Bean
    public WebClient tmdbClientBuilder() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}