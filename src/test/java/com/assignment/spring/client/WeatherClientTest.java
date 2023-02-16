package com.assignment.spring.client;

import com.assignment.spring.config.ServiceConfig;
import com.assignment.spring.mapper.WeatherEntityMapper;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.model.WeatherDTO;
import com.assignment.spring.model.WeatherResponse;
import com.assignment.spring.util.JsonHelperClass;
import com.assignment.spring.util.JsonJUnitHelperIOClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Slf4j
public class WeatherClientTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ServiceConfig serviceConfig;

    @Autowired
    JsonHelperClass jsonHelperClass;

    @Autowired
    JsonJUnitHelperIOClass jsonJUnitHelperIOClass;

    @Autowired
    WeatherMapper weatherMapper;
    @Autowired
    WeatherClient client;

    private MockRestServiceServer restServer;
    private ObjectMapper objectMapper;

    public static final String BUCHAREST = "Bucharest";


    @Before
    public void initMockRestServiceServer() {
        restServer = MockRestServiceServer.bindTo(restTemplate).bufferContent().build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void findWeatherInfoByCityTest() throws Exception {
        UriComponents uriCompForWeatherInfo = UriComponentsBuilder.newInstance()
                .scheme(serviceConfig.getOpenweatherApi().getServiceSchema())
                .host(serviceConfig.getOpenweatherApi().getHostUri())
                .path(serviceConfig.getOpenweatherApi().getWeatherContext())
                .queryParam("q", BUCHAREST)
                .queryParam("units", "metric")
                .queryParam("appid", serviceConfig.getOpenweatherApi().getAppId())
                .build()
                .encode();

        WeatherResponse weatherResponse =
                jsonJUnitHelperIOClass.getJsonObject(
                        "json-data/weather-data-by-city.json",
                        new TypeReference<WeatherResponse>(){}
                );
        log.info("Server Uri: {}", uriCompForWeatherInfo);
        restServer.expect(requestTo(uriCompForWeatherInfo.toString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(weatherResponse)));

        ResponseEntity<WeatherDTO> responseEntity = ResponseEntity.ok(
                weatherMapper.map(
                        WeatherEntityMapper.map(client.findWeatherInfoByCity(BUCHAREST))));
        restServer.verify();
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        WeatherDTO receivedWeatherDTO = jsonHelperClass.jsonToObject(
                objectMapper.writeValueAsString(responseEntity.getBody()),
                new TypeReference<WeatherDTO>(){}
        );
        assertTrue(receivedWeatherDTO.getCity().equals(BUCHAREST));
        assertEquals(receivedWeatherDTO.getCity(), weatherResponse.getName());
    }
}
