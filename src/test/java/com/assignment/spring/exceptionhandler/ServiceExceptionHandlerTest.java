package com.assignment.spring.exceptionhandler;

import com.assignment.spring.controller.WeatherController;
import com.assignment.spring.exceptionhandler.advice.ServiceExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@EnableWebMvc
public class ServiceExceptionHandlerTest {


    private MockMvc mockMvc;

    @Mock
    WeatherController weatherControllerMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherControllerMock)
                .setControllerAdvice(new ServiceExceptionHandler())
                .build();
    }

    @Test()
    public void testGlobalExceptionHandlerError() throws Exception {
        Mockito.when(weatherControllerMock.weather("89d7sa98"))
               .thenThrow(new ResourceNotFoundException("Weather info was not found for city: 89d7sa98"));
        mockMvc.perform(get("/openweather-api/v1/weather?city=89d7sa98")).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
