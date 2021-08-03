package com.assignment.spring.controller;

import com.assignment.spring.api.config.Constants;
import com.assignment.spring.WeatherRepository;
import com.assignment.spring.api.model.WeatherResponse;
import com.assignment.spring.api.mapper.WeatherEntityMapper;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {

    private RestTemplate restTemplate;
    private WeatherRepository weatherRepository;

    @RequestMapping("/weather/{city}")
    public ResponseEntity<WeatherEntity> weather(HttpServletRequest request,
                                                 @PathVariable("city") String city) {
        String url = Constants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", Constants.APP_ID);
        ResponseEntity<WeatherResponse> response = null;

        try {
            response = restTemplate.getForEntity(url, WeatherResponse.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Weather info was not found for city: " + city);
        }

        return new ResponseEntity<WeatherEntity>(WeatherEntityMapper.map(response.getBody()), HttpStatus.OK);
    }
}