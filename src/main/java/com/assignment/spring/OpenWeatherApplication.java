package com.assignment.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;

@SpringBootApplication
@Slf4j
public class OpenWeatherApplication {

	@Autowired
	static BuildProperties buildProperties;

	public static void main(String[] args) {
		SpringApplication.run(OpenWeatherApplication.class, args);
		displayVersion();
	}

	private static void displayVersion() {
		log.info("--------------------------------------------------------------");
		log.info("Application {} version {} STARTED", buildProperties.getName(), buildProperties.getVersion());
		log.info("--------------------------------------------------------------");
	}

}
