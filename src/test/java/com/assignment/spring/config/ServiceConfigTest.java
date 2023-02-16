package com.assignment.spring.config;

import com.assignment.spring.model.config.OpenweatherApi;
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
public class ServiceConfigTest {

    @Autowired
    ServiceConfig serviceConfig;

    @Test
    public void testInitialization() {
        assertEquals(serviceConfig.getAppVersion(), "1.0.0");
    }

    @Test
    public void testConstructor() {
        ServiceConfig serviceConfig = new ServiceConfig();
        assertNotNull(serviceConfig);
    }

    @Test
    public void testBuilder() {
        OpenweatherApi openweatherApi = OpenweatherApi.builder().appId("test27678").build();
        assertNotNull(serviceConfig.builder().toString());
        ServiceConfig serviceConfig = ServiceConfig.builder()
                .appVersion("2.0")
                .openweatherApi(openweatherApi)
                .build();
        assertNotNull(serviceConfig.toString());
        assertEquals(serviceConfig.getAppVersion(), "2.0");
        assertNotNull(openweatherApi.toString());
        assertEquals(serviceConfig.getOpenweatherApi().getAppId(), "test27678");
    }

    @Test
    public void testEquals() {
        ServiceConfig serviceConfig1 = new ServiceConfig();
        serviceConfig1.setAppVersion("1.0");
        ServiceConfig serviceConfig2 = serviceConfig.builder().appVersion("2.0").build();
        assertFalse(serviceConfig1.equals(serviceConfig2));
    }
}