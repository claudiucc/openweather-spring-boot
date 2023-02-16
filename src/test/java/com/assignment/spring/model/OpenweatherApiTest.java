
package com.assignment.spring.model;

import com.assignment.spring.model.config.OpenweatherApi;
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
public class OpenweatherApiTest {


    @Test
    public void testEquals() {
        OpenweatherApi o1 = new OpenweatherApi();
        OpenweatherApi o2 = new OpenweatherApi();
        o1.setServiceSchema("http");
        o2.setServiceSchema("http");
        assertTrue(o1.equals(o2));
        assertTrue(o1.hashCode() == o2.hashCode());
    }
}
