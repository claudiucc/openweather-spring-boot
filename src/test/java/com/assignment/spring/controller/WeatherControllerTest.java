package com.assignment.spring.controller;


import com.assignment.spring.config.ServiceConfig;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.model.WeatherDTO;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.service.WeatherService;
import com.assignment.spring.util.JsonHelperClass;
import com.assignment.spring.util.JsonJUnitHelperIOClass;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @MockBean
    WeatherService mockWeatherService;

    @Autowired
    ServiceConfig serviceConfig;

    @Autowired
    JsonJUnitHelperIOClass jsonJUnitHelperIOClass;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JsonHelperClass jsonHelper;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    WeatherMapper weatherMapper;

    public static final String BUCHAREST = "Bucharest";
    public static final String WEATHER_CONTEXT_PATH = "Bucharest";

    @Test
    @WithMockUser(username = "claudiu", password = "pass123", roles = "USER")
    public void getWeatherByCityTest() throws Exception {

        WeatherDTO weatherResponse =
                jsonJUnitHelperIOClass.getJsonObject(
                        "json-data/weather-response.json",
                        new TypeReference<WeatherDTO>() {}
                );

        when(mockWeatherService.findWeatherInfoByCity(BUCHAREST)).thenReturn(weatherResponse);

        MvcResult result = mockMvc.perform(
                get("/openweather-api/v1/weather")
                .param("city", BUCHAREST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        WeatherDTO retrievedWeatherDTO = jsonHelper.jsonToObject(
                result.getResponse().getContentAsString(),
                new TypeReference<WeatherDTO>() {}
        );
        assertTrue(retrievedWeatherDTO.getCity() != null);
        assertEquals(retrievedWeatherDTO.getCity(), weatherResponse.getCity());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = "ADMIN")
    public void getWeatherInfoByCityTest() throws Exception {

        WeatherDTO weatherDTO1 = WeatherDTO.builder()
                .city(BUCHAREST)
                .country("RO")
                .temperature(23.2)
                .build();

        WeatherDTO weatherDTO2 = WeatherDTO.builder()
                .city(BUCHAREST)
                .country("RO")
                .temperature(32.5)
                .build();

        weatherRepository.saveAll(weatherMapper.mapToEntityList(Arrays.asList(weatherDTO1, weatherDTO2)));

        List<WeatherDTO> weatherResponse =
                jsonJUnitHelperIOClass.getJsonObject(
                        "json-data/weather-response-list.json",
                        new TypeReference<List<WeatherDTO>>() {}
                );

        when(mockWeatherService.findAllWeatherInfoByCity(BUCHAREST)).thenReturn(weatherResponse);

        MvcResult result = mockMvc.perform(
                get("/openweather-api/v1/weatherInfo/" + BUCHAREST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<WeatherDTO> retrievedWeatherInfo = jsonHelper.jsonToObject(
                result.getResponse().getContentAsString(),
                new TypeReference<List<WeatherDTO>>() {}
        );
        assertTrue(retrievedWeatherInfo != null && retrievedWeatherInfo.size() == 2);
    }
}
