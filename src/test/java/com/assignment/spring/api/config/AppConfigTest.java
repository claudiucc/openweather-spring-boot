package com.assignment.spring.api.config;

import com.assignment.spring.api.model.OpenweatherApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Slf4j
public class AppConfigTest {

    @Autowired
    AppConfig appConfig;

    @Test
    public void testInitialization() {
        assertEquals(appConfig.getAppVersion(), "1.0");
    }

    @Test
    public void testConstructor() {
        AppConfig appConfig = new AppConfig();
        assertNotNull(appConfig);
    }

    @Test
    public void testBuilder() {
        OpenweatherApi openweatherApi = OpenweatherApi.builder().appId("test27678").build();
        assertNotNull(AppConfig.builder().toString());
        AppConfig appConfig = AppConfig.builder()
                .appVersion("2.0")
                .openweatherApi(openweatherApi)
                .build();
        assertNotNull(appConfig.toString());
        assertEquals(appConfig.getAppVersion(), "2.0");
        assertNotNull(openweatherApi.toString());
        assertEquals(appConfig.getOpenweatherApi().getAppId(), "test27678");
    }

    @Test
    public void testEquals() {
        AppConfig appConfig1 = new AppConfig();
        appConfig1.setAppVersion("1.0");
        AppConfig appConfig2 = AppConfig.builder().appVersion("2.0").build();
        assertFalse(appConfig1.equals(appConfig2));
    }
}
