
package com.suryatejaduggi.weather.controller;

import com.suryatejaduggi.weather.dto.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.suryatejaduggi.weather.service.WeatherService;

import java.util.List;

@RestController
public class WeatherContoller {


	WeatherService weatherService = new WeatherService();

    @GetMapping("/weather")
	public ResponseEntity<List<WeatherData>> index(@RequestParam int zipcode) {
		return weatherService.getWeatherData(zipcode);
	}
 
}
