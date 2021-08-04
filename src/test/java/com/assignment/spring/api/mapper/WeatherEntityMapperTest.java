package com.assignment.spring.api.mapper;

import com.assignment.spring.api.model.Main;
import com.assignment.spring.api.model.Sys;
import com.assignment.spring.api.model.WeatherResponse;
import com.assignment.spring.entity.WeatherEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherEntityMapperTest {

    @Test
    public void testConstructor() {
        WeatherEntityMapper  weatherEntityMapper = new WeatherEntityMapper();
        assertNotNull(weatherEntityMapper);
    }

    @Test
    public void testMap() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setName("Viena");
        weatherResponse.setMain(Main.builder().temp(32.2).build());
        weatherResponse.setSys(Sys.builder().country("Austria").build());
        WeatherEntity weatherEntity = WeatherEntityMapper.map(weatherResponse);
        assertEquals(weatherEntity.getTemperature(), Double.valueOf(32.2));
    }
}
