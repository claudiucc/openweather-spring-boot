package com.assignment.spring.service;


import com.assignment.spring.client.WeatherClient;
import com.assignment.spring.mapper.WeatherEntityMapper;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.model.WeatherDTO;
import com.assignment.spring.model.WeatherResponse;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.util.JsonJUnitHelperIOClass;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherServiceTest {


    @MockBean
    WeatherClient client;

    @Autowired
    JsonJUnitHelperIOClass jsonJUnitHelperIOClass;

    @Autowired
    WeatherService weatherService;

    @MockBean
    WeatherService weatherServiceMock;

    @Mock
    WeatherRepository weatherRepository;

    @Autowired
    WeatherMapper weatherMapper;

    public static final String BUCHAREST = "Bucharest";

    @Test
    public void weatherByCityTest() throws Exception {

        WeatherResponse weatherResponse =
                jsonJUnitHelperIOClass.getJsonObject(
                        "json-data/weather-data-by-city.json",
                        new TypeReference<WeatherResponse>() {}
                );

        when(client.findWeatherInfoByCity(BUCHAREST)).thenReturn(weatherResponse);
        when(weatherServiceMock.findWeatherInfoByCity(BUCHAREST)).thenReturn(weatherMapper.map(WeatherEntityMapper.map(weatherResponse)));

        WeatherDTO retrievedWeatherDTO = weatherService.findWeatherInfoByCity(BUCHAREST);
        assertThat(retrievedWeatherDTO.getCity() != null);
        assertEquals(retrievedWeatherDTO.getCity(), weatherResponse.getName());
    }

    @Test
    public void weatherInfoByCityTest() throws Exception {

        List<WeatherDTO> weatherDTOList =
                jsonJUnitHelperIOClass.getJsonObject(
                        "json-data/weather-response-list.json",
                        new TypeReference<List<WeatherDTO>>() {}
                );

        when(weatherRepository.findByCity(BUCHAREST)).thenReturn(weatherMapper.mapToEntityList(weatherDTOList));
        when(weatherServiceMock.findAllWeatherInfoByCity(BUCHAREST)).thenReturn(weatherMapper.mapToList(weatherMapper.mapToEntityList(weatherDTOList)));

        List<WeatherDTO> retrievedWeatherDTOList = weatherService.findAllWeatherInfoByCity(BUCHAREST);
        assertTrue(retrievedWeatherDTOList != null && retrievedWeatherDTOList.size() == 2);
    }
}
