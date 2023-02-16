
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
public class ErrorDTOTest {


    @Test
    public void testEquals() {
        ErrorDTO e1 = new ErrorDTO();
        ErrorDTO e2 = new ErrorDTO();
        e1.setMessage("msg1");
        e2.setMessage("msg1");
        assertTrue(e1.equals(e2));
        assertTrue(e1.hashCode() == e2.hashCode());
    }
}
