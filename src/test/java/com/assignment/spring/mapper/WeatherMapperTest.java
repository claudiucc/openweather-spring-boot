package com.assignment.spring.mapper;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.model.WeatherDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WeatherMapperImpl.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherMapperTest {

    @Autowired
    private WeatherMapper weatherMapper;

    @Test
    public void testInjection() {
        assertNotNull(weatherMapper);
    }

    @Test
    public void testMap() {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCity("Bucharest");
        weatherEntity.setCountry("Romania");
        weatherEntity.setTemperature(22.7);
        weatherEntity.setId(5);
        WeatherDTO weatherDTO = weatherMapper.map(weatherEntity);
        assertEquals(weatherDTO.getCity(), "Bucharest");
    }

    @Test
    public void testMapReverse() {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCity("Bucharest");
        weatherDTO.setCountry("Romania");
        weatherDTO.setTemperature(22.7);
        weatherDTO.setId(5);
        WeatherEntity weatherEntity = weatherMapper.map(weatherDTO);
        assertEquals(weatherEntity.getCountry(), "Romania");
    }
}
