package com.assignment.spring.exceptionhandler;

import com.assignment.spring.controller.WeatherController;
import com.assignment.spring.exceptionhandler.advice.APIExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@EnableWebMvc
public class APIExceptionHandlerTest {


    private MockMvc mockMvc;

    @Mock
    WeatherController weatherControllerMock;

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherControllerMock)
                .setControllerAdvice(new APIExceptionHandler())
                .build();
    }

    @Test()
    public void testGlobalExceptionHandlerError() throws Exception {
        Mockito.when(weatherControllerMock.weather(request, "89d7sa98"))
               .thenThrow(new RuntimeException("Weather info was not found for city: 89d7sa98"));
        mockMvc.perform(get("/openweather-api/v1/weather?city=89d7sa98")).andExpect(status().is(200));
    }
}
