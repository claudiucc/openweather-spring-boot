package com.assignment.spring.controller;

import com.assignment.spring.api.model.WeatherDTO;
import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import com.assignment.spring.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/openweather-api/v1")
@Slf4j
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherDTO> weather(HttpServletRequest request,
                                              @RequestParam("city") String city) throws Exception {
        WeatherDTO weatherDTO = weatherService.findWeatherInfoByCity(city);

        if(weatherDTO == null)
            throw new ResourceNotFoundException("Weather info was not found for city: " + city);

        return new ResponseEntity<WeatherDTO>(weatherDTO, HttpStatus.OK);
    }
}