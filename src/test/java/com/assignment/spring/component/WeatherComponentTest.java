package com.assignment.spring.component;

import com.assignment.spring.api.model.Main;
import com.assignment.spring.api.model.Sys;
import com.assignment.spring.api.model.WeatherResponse;
import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherComponentTest {

    @Mock
    WeatherComponent weatherComponentMock;

    @Autowired
    WeatherComponent weatherComponent;

    @Test
    public void findWeatherInfoByCityMockTest() {
        when(weatherComponentMock.findWeatherInfoByCity("Vienna")).thenReturn(buildWeatherResponse());
    }

    @Test
    public void findWeatherInfoByCityTest() {
        WeatherResponse weatherResponse = weatherComponent.findWeatherInfoByCity("Vienna");
        assertEquals(weatherResponse.getName(), "Vienna");
    }

    @Test
    public void findWeatherInfoByCityNotFoundTest() {
        boolean thrown = false;
        try {
            weatherComponent.findWeatherInfoByCity("test2727827");
        } catch (ResourceNotFoundException e) {
            thrown = true;
            assertEquals(e.getMessage(), "Weather info was not found for city: test2727827");
        }
        assertTrue(thrown);
    }

    private WeatherResponse buildWeatherResponse() {
        return WeatherResponse.builder()
                .name("Vienna")
                .main(Main.builder().temp(32.2).build())
                .sys(Sys.builder().country("Austria").build())
                .build();
    }
}
