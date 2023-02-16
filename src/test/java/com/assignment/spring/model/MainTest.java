
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
public class MainTest {


    @Test
    public void testEquals() {
        Main s1 = new Main();
        Main s2 = new Main();
        s1.setHumidity(2);
        s2.setHumidity(2);
        assertTrue(s1.equals(s2));
        assertTrue(s2.hashCode() == s1.hashCode());
    }
}
