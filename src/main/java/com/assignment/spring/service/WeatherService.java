package com.assignment.spring.service;

import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.api.mapper.WeatherEntityMapper;
import com.assignment.spring.api.mapper.WeatherMapper;
import com.assignment.spring.api.model.WeatherDTO;
import com.assignment.spring.component.WeatherComponent;
import com.assignment.spring.entity.WeatherEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherComponent weatherComponent;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherDTO findWeatherInfoByCity(String city) throws Exception {
        WeatherEntity weatherEntity = WeatherEntityMapper.map(weatherComponent.findWeatherInfoByCity(city));
        saveWeatherInfo(weatherEntity);
        return weatherMapper.map(weatherEntity);
    }

    public WeatherDTO saveWeatherInfo(WeatherEntity entity) {
        return weatherMapper.map(weatherRepository.save(entity));
    }
}
