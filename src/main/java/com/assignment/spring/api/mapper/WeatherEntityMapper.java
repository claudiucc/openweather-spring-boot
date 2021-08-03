package com.assignment.spring.api.mapper;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.api.model.WeatherResponse;

public class WeatherEntityMapper {

    public static WeatherEntity map(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCity(response.getName());
        entity.setCountry(response.getSys().getCountry());
        entity.setTemperature(response.getMain().getTemp());

        return entity;
    }
}
