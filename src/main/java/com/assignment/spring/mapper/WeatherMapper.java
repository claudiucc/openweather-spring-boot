package com.assignment.spring.mapper;

import com.assignment.spring.model.WeatherDTO;
import com.assignment.spring.entity.WeatherEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherDTO map(WeatherEntity source);

    @InheritInverseConfiguration
    WeatherEntity map(WeatherDTO source);

    List<WeatherDTO> mapToList(List<WeatherEntity> source);

    @InheritInverseConfiguration
    List<WeatherEntity> mapToEntityList(List<WeatherDTO> source);
}