package com.assignment.spring.mapper;

import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.model.Main;
import com.assignment.spring.model.Sys;
import com.assignment.spring.model.WeatherResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherEntityMapperTest {

    @Test
    public void testConstructor() {
        WeatherEntityMapper weatherEntityMapper = new WeatherEntityMapper();
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
