package com.assignment.spring.api.config;

import com.assignment.spring.api.model.OpenweatherApi;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(ignoreInvalidFields = true, prefix = AppConfig.PREFIX)
@Configuration
public class AppConfig {
    public static final String PREFIX = "props";

    private String appVersion;
    private OpenweatherApi openweatherApi;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    private void init() {}
}
