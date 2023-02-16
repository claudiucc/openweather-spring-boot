
package com.assignment.spring.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WeatherResponseTest {


    @Test
    public void testEquals() {
        WeatherResponse wr1 = new WeatherResponse();
        WeatherResponse wr2 = new WeatherResponse();
        wr1.setName("city");
        wr2.setName("city");
        assertTrue(wr1.equals(wr2));
        assertTrue(wr1.hashCode() == wr2.hashCode());
    }
}
