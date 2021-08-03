package com.assignment.spring.controller;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {

    private WeatherService weatherService;

    @RequestMapping("/weather/{city}")
    public ResponseEntity<WeatherEntity> weather(HttpServletRequest request,
                                                 @PathVariable("city") String city) throws Exception {
        return new ResponseEntity<WeatherEntity>(weatherService.findWeatherInfoByCity(city), HttpStatus.OK);
    }
}