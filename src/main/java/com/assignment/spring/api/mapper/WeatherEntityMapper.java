package com.assignment.spring.api.mapper;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.api.model.WeatherResponse;

public class WeatherEntityMapper {

    public static WeatherEntity map(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        if(response.getName() != null)
            entity.setCity(response.getName());
        if(response.getSys() != null && response.getSys().getCountry() != null)
            entity.setCountry(response.getSys().getCountry());
        if(response.getMain() != null && response.getMain().getTemp() != null)
            entity.setTemperature(response.getMain().getTemp());

        return entity;
    }
}
