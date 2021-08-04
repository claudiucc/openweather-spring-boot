package com.assignment.spring.controller;


import com.assignment.spring.api.mapper.WeatherMapper;
import com.assignment.spring.api.model.WeatherDTO;
import com.assignment.spring.api.model.WeatherResponse;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Mock
    WeatherController weatherControllerMock;

    @Mock
    HttpServletRequest request;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WeatherController weatherController;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    WeatherMapper weatherMapper;

    @MockBean
    Clock clock;

    @Test
    public void weather() throws Exception {
        when(weatherControllerMock.weather(request, "Bucharest")).thenReturn(buildResponseEntity());
    }

    @Test
    public void SpringBootJPAIntegrationTest() throws Exception {
        mockMvc.perform(get("/openweather-api/v1/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .param("city", "Bucharest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.temperature").exists())
                .andExpect(jsonPath("$.country").exists())
                .andExpect(jsonPath("$.city").exists())
                .andExpect(jsonPath("$.city").value("Bucharest"));

        Optional<WeatherEntity> weatherEntity = weatherRepository.findById(1);
        assertThat(weatherEntity.get().getCity(), is(equalTo("Bucharest")));
        assertThat(weatherEntity.get().getCity(), is(equalTo("Bucharest")));
        assertThat(weatherEntity.get().getId(), is(equalTo(1)));
    }

    @Test
    public void SpringBootJPAIntegrationTest2() throws Exception {
        WeatherDTO weatherDTO = weatherMapper.map(weatherRepository.save(buildWeatherEntity()));
        assertThat(weatherDTO.getCity(), is(equalTo("Munich")));
    }

    @Test
    public void findWeatherInfoByCityTest() {
        boolean thrown = false;
        ResponseEntity<WeatherDTO> responseEntity = null;
        try {
            responseEntity = weatherController.weather(request, "Bucharest");
        } catch (Exception e) {
            thrown = true;
        }
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode().value(), 200);
        assertEquals(responseEntity.getBody().getCity(), "Bucharest");
    }


    private ResponseEntity<WeatherDTO> buildResponseEntity() {
        return new ResponseEntity<WeatherDTO>(WeatherDTO.builder()
                .city("Bucharest")
                .temperature(22.7)
                .country("Romania")
                .build(), HttpStatus.OK);
    }

    private WeatherEntity buildWeatherEntity() {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setId(762);
        weatherEntity.setTemperature(27.1);
        weatherEntity.setCountry("DE");
        weatherEntity.setCity("Munich");
        return weatherEntity;
    }
}
