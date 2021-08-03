package com.assignment.spring.component;


import com.assignment.spring.api.model.WeatherResponse;
import com.assignment.spring.api.util.AppConstants;
import com.assignment.spring.entity.WeatherEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class WeatherComponent {

    private RestTemplate restTemplate;

    public WeatherResponse findWeatherInfoByCity(String city) {
        log.info("WeatherComponent.findWeatherInfoByCity method START with city param: {}", city);
        String url = AppConstants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", AppConstants.APP_ID);

        MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();
        httpHeaders.add(AppConstants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(AppConstants.ACCEPT_HEADER, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity request = new HttpEntity(httpHeaders);
        ResponseEntity<WeatherResponse> response = null;

        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    WeatherResponse.class
            );
            log.info("WeatherComponent.findWeatherInfoByCity OpenWeather API responded: {}", response.getBody());
        } catch (Exception e) {
            log.error("WeatherComponent.findWeatherInfoByCity: OpenWeather API responded: {}", e.getMessage());
            throw e;
        }

        return response.getBody();
    }
}
