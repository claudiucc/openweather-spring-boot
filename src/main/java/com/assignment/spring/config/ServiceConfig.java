package com.assignment.spring.config;

import com.assignment.spring.model.auth.Authentication;
import com.assignment.spring.model.config.OpenweatherApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(ignoreInvalidFields = true, prefix = ServiceConfig.PREFIX)
@Configuration
public class ServiceConfig {
    public static final String PREFIX = "props";

    private String appVersion;
    private OpenweatherApi openweatherApi;
    private Authentication authentication;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}