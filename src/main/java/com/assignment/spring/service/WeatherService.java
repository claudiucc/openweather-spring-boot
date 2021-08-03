package com.assignment.spring.service;

import com.assignment.spring.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
}
