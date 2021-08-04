package com.assignment.spring.service;

import com.assignment.spring.api.mapper.WeatherMapper;
import com.assignment.spring.api.model.WeatherDTO;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.util.Optional;

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

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherMapper weatherMapper;

    @Mock
    private Clock clock;

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

    @Test
    public void SpringBootIntegrationTest() throws Exception {
        when(weatherRepository.findById(2346)).thenReturn(Optional.of(buildWeatherEntity()));
        WeatherEntity weatherEntity = buildWeatherEntity();
        when(weatherMapper.map(weatherEntity)).thenReturn(buildWeatherDTO());

        WeatherDTO newWeatherInfo = weatherService.saveWeatherInfo(buildWeatherEntity());
        WeatherDTO weatherFromDB = weatherService.findById(1);

        assertEquals(weatherFromDB.getCity(), newWeatherInfo.getCity());
        assertEquals(weatherFromDB.getCountry(), newWeatherInfo.getCountry());
    }

    @Test
    public void weatherEntityNotPresentReturnExceptionTest() {
        // given
        when(weatherRepository.findById(2331)).thenReturn(Optional.empty());
        String message = "";
        try {
            WeatherDTO weatherDTO = weatherService.findById(2331);
        } catch (Exception e) {
            message = e.getMessage();
        }

        String expectedMessage = "Weather entity with id: 2331 was not found!";

        // then
        assertTrue(message.contains(expectedMessage));
    }

    private WeatherDTO buildWeatherDTO() {
        return WeatherDTO.builder()
                .city("Napoli")
                .temperature(24.9)
                .country("Italy")
                .build();
    }

    private WeatherEntity buildWeatherEntity() {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setTemperature(24.9);
        weatherEntity.setCountry("Italy");
        weatherEntity.setCity("Napoli");
        return weatherEntity;
    }
}
