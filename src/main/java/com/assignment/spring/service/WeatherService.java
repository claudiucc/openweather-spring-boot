package com.assignment.spring.service;

import com.assignment.spring.WeatherRepository;
import com.assignment.spring.api.mapper.WeatherEntityMapper;
import com.assignment.spring.component.WeatherComponent;
import com.assignment.spring.entity.WeatherEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherComponent weatherComponent;
    private final WeatherRepository weatherRepository;

    public WeatherEntity findWeatherInfoByCity(String city) throws Exception {
        WeatherEntity weatherEntity = WeatherEntityMapper.map(weatherComponent.findWeatherInfoByCity(city));
        saveWeatherInfo(weatherEntity);
        return WeatherEntityMapper.map(weatherComponent.findWeatherInfoByCity(city));
    }

    public WeatherEntity saveWeatherInfo(WeatherEntity entity) {
        return weatherRepository.save(entity);
    }
}
