
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
public class CoordTest {


    @Test
    public void testEquals() {
        Coord s1 = new Coord();
        Coord s2 = new Coord();
        s1.setLat(2.12);
        s2.setLat(2.12);
        assertTrue(s1.equals(s2));
        assertTrue(s2.hashCode() == s1.hashCode());
    }
}
