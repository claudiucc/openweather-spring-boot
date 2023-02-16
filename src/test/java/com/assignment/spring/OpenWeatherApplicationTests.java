package com.assignment.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OpenWeatherApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void main() {
		OpenWeatherApplication.main(new String[] {});
	}
}
