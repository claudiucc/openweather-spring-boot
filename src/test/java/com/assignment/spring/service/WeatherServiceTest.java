package com.assignment.spring.service;

import com.assignment.spring.api.model.WeatherDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherServiceTest {

    @Mock
    WeatherService weatherServiceMock;

    @Autowired
    WeatherService weatherService;

    @Test
    public void findWeatherInfoByCityMockTest() throws Exception {
        when(weatherServiceMock.findWeatherInfoByCity("Bucharest")).thenReturn(buildWeatherDTO());
    }

    @Test
    public void findWeatherInfoByCityTest() throws Exception {
        WeatherDTO weatherDTO = weatherService.findWeatherInfoByCity("Bucharest");
        assertEquals(weatherDTO.getCity(), "Bucharest");
    }

    @Test
    public void findWeatherInfoByCityNotFoundTest() {
        boolean thrown = false;
        try {
            weatherService.findWeatherInfoByCity("test111111");
        } catch (Exception e) {
            thrown = true;
            assertEquals(e.getMessage(), "Weather info was not found for city: test111111");
        }
        assertTrue(thrown);
    }

    private WeatherDTO buildWeatherDTO() {
        return WeatherDTO.builder()
                .city("Bucharest")
                .temperature(22.7)
                .country("Romania")
                .build();
    }
}
