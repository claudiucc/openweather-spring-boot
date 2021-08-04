package com.assignment.spring.component;


import com.assignment.spring.api.config.AppConfig;
import com.assignment.spring.api.model.WeatherResponse;
import com.assignment.spring.api.util.AppConstants;
import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
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

    private AppConfig appConfig;
    private RestTemplate restTemplate;

    public WeatherResponse findWeatherInfoByCity(String city) {
        log.info("WeatherComponent.findWeatherInfoByCity method START with city param: {}", city);

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(appConfig.getOpenweatherApi().getServiceSchema());
        uriBuilder.setHost(appConfig.getOpenweatherApi().getHostUri());
        uriBuilder.setPath(appConfig.getOpenweatherApi().getWeatherContext());
        uriBuilder.addParameter("q", city);
        uriBuilder.addParameter("units", "metric");
        uriBuilder.addParameter("appid", appConfig.getOpenweatherApi().getAppId());

        MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();
        httpHeaders.add(AppConstants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(AppConstants.ACCEPT_HEADER, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity request = new HttpEntity(httpHeaders);
        ResponseEntity<WeatherResponse> response = null;

        try {
            response = restTemplate.exchange(
                    uriBuilder.build().toURL().toString(),
                    HttpMethod.GET,
                    request,
                    WeatherResponse.class
            );
            log.info("WeatherComponent.findWeatherInfoByCity OpenWeather API responded: {}", response.getBody());
        } catch (Exception e) {
            log.error("WeatherComponent.findWeatherInfoByCity: OpenWeather API responded: {}", e.getMessage());
            throw new ResourceNotFoundException("Weather info was not found for city: " + city);
        }

        return response.getBody();
    }
}
