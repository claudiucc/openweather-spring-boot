
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
public class WindTest {


    @Test
    public void testEquals() {
        Wind s1 = new Wind();
        Wind s2 = new Wind();
        s1.setSpeed(32.45);
        s2.setSpeed(32.45);
        assertTrue(s1.equals(s2));
        assertTrue(s2.hashCode() == s1.hashCode());
    }
}
