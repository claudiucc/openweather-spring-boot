package com.assignment.spring.service;

import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.api.mapper.WeatherEntityMapper;
import com.assignment.spring.api.mapper.WeatherMapper;
import com.assignment.spring.api.model.WeatherDTO;
import com.assignment.spring.component.WeatherComponent;
import com.assignment.spring.entity.WeatherEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {

    private final WeatherComponent weatherComponent;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherDTO findWeatherInfoByCity(String city) throws Exception {
        WeatherEntity weatherEntity = WeatherEntityMapper.map(weatherComponent.findWeatherInfoByCity(city));
        return saveWeatherInfo(weatherEntity);
    }

    public WeatherDTO findById(Integer id) {
        try {
            return weatherMapper.map(weatherRepository.findById(id).get());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResourceNotFoundException("Weather entity with id: " + id + " was not found!");
        }
    }

    public WeatherDTO saveWeatherInfo(WeatherEntity entity) {
        return weatherMapper.map(weatherRepository.save(entity));
    }
}
