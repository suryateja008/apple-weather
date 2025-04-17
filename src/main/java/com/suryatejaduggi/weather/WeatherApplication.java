package com.suryatejaduggi.weather;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suryatejaduggi.weather.service.WeatherService;

@SpringBootApplication
public class WeatherApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}


}
