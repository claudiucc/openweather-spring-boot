
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
public class SysTest {


    @Test
    public void testEquals() {
        Sys s1 = new Sys();
        Sys s2 = new Sys();
        s1.setCountry("country");
        s1.setMessage(2.2);
        s2.setCountry("country");
        s2.setMessage(2.2);
        assertTrue(s1.equals(s2));
        assertTrue(s2.hashCode() == s1.hashCode());
    }
}
