
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
public class WeatherDTOTest {

    Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Before
    public void init() {
        additionalProperties.put("key", "VALUE");
    }

    @Test
    public void testConstructor() {
        WeatherDTO WeatherDTO = new WeatherDTO(1, "2", "3", 3.22);
        assertNotNull(WeatherDTO);
    }

    @Test
    public void testBuilder() {
        WeatherDTO weatherDTO = WeatherDTO.builder()
                .id(1)
                .city("city")
                .country("country")
                .build();
        assertTrue(weatherDTO.getCity().equals("city"));
    }

    @Test
    public void testEquals() {
        WeatherDTO w1 = new WeatherDTO();
        WeatherDTO w2 = new WeatherDTO();
        w1.setCity("c1");
        w2.setCity("c1");
        assertTrue(w1.equals(w2));
        assertTrue(w1.hashCode() == w2.hashCode());
    }
}
