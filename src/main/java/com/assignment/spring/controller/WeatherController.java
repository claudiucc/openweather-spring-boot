package com.assignment.spring.controller;

import com.assignment.spring.model.WeatherDTO;
import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import com.assignment.spring.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/openweather-api/v1")
@Slf4j
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherDTO> weather(@RequestParam("city") @NotBlank String city) throws Exception {
        WeatherDTO weatherDTO = weatherService.findWeatherInfoByCity(city);

        if(weatherDTO == null) {
            throw new ResourceNotFoundException("Weather info was not found for city: " + city);
        }

        return new ResponseEntity<WeatherDTO>(weatherDTO, HttpStatus.OK);
    }

    @GetMapping("/weatherInfo/{city}")
    public ResponseEntity<List<WeatherDTO>> weatherInfoByCity(@PathVariable("city") @NotBlank String city) throws Exception {
        List<WeatherDTO> weatherDTOs = weatherService.findAllWeatherInfoByCity(city);

        if(weatherDTOs == null || weatherDTOs.size() == 0) {
            throw new ResourceNotFoundException("Weather info was not found for city: " + city);
        }

        return new ResponseEntity<List<WeatherDTO>>(weatherDTOs, HttpStatus.OK);
    }
}