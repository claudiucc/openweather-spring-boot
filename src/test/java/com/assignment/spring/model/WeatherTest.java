
package com.assignment.spring.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherTest {

    Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Before
    public void init() {
        additionalProperties.put("key", "VALUE");
    }

    @Test
    public void testConstructor() {
        Weather weather = new Weather(1, "2", "3", "4", additionalProperties);
        assertNotNull(weather);
    }

    @Test
    public void testBuilder() {
        Weather weather = Weather.builder()
                .additionalProperties(additionalProperties)
                .id(1)
                .icon("icon")
                .main("main")
                .build();
        assertTrue(weather.getIcon().equals("icon"));
    }

    @Test
    public void testEquals() {
        Weather w1 = new Weather();
        Weather w2 = new Weather();
        w1.setIcon("icon");
        w2.setIcon("icon");
        assertTrue(w1.equals(w2));
        assertTrue(w1.hashCode() == w2.hashCode());
    }
}
