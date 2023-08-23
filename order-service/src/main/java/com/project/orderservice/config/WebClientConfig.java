package com.project.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient bookClient() {
        return WebClient.create("http://localhost:8090");
    }

    @Bean
    public WebClient discountClient() {
        return WebClient.create("http://localhost:8089");
    }
}
