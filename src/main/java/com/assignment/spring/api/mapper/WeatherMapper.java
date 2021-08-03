package com.assignment.spring.api.mapper;

import com.assignment.spring.api.model.WeatherDTO;
import com.assignment.spring.entity.WeatherEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherDTO map(WeatherEntity source);

    @InheritInverseConfiguration
    WeatherEntity map(WeatherDTO source);
}
