
package com.assignment.spring.model;

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
public class CloudsTest {


    @Test
    public void testConstructor() {
        Map<String, Object> additionalProperties = new HashMap<String, Object>();
        Clouds clouds = new Clouds(1, additionalProperties);
        assertNotNull(clouds);
    }

    @Test
    public void testBuilder() {
        Clouds clouds = Clouds.builder()
                .additionalProperties(new HashMap<String, Object>())
                .all(29889)
                .build();
        assertTrue(clouds.getAll() == 29889);
    }

    @Test
    public void testEquals() {
        Clouds c1 = new Clouds();
        c1.setAll(20);
        Map<String, Object> additionalProperties = new HashMap<String, Object>();
        additionalProperties.put("NAME", "VALUE");
        c1.setAdditionalProperties(additionalProperties);
        Clouds c2 = Clouds.builder().all(20).build();
        c2.setAdditionalProperties(additionalProperties);
        assertTrue(c1.equals(c2));
        assertTrue(c1.hashCode() == c2.hashCode());
    }
}
