package com.assignment.spring.service;

import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.mapper.WeatherEntityMapper;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.model.WeatherDTO;
import com.assignment.spring.client.WeatherClient;
import com.assignment.spring.entity.WeatherEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherDTO findWeatherInfoByCity(String city) throws Exception {
        WeatherEntity weatherEntity = WeatherEntityMapper.map(weatherClient.findWeatherInfoByCity(city));
        return saveWeatherInfo(weatherEntity);
    }

    public List<WeatherDTO> findAllWeatherInfoByCity(String city) throws Exception {
        return weatherMapper.mapToList(weatherRepository.findByCity(city));
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