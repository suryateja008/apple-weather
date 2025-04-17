package com.suryatejaduggi.weather.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.suryatejaduggi.weather.dto.WeatherData;
import com.google.common.cache.LoadingCache;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Generate and get dummy weather for given pincodes.
 */
@Service
public class WeatherService {
    
    /**
     * dummy zipcodes to test the request and response.
     */
    static Set<Integer> zipCodes = new HashSet<>();

    static {
         zipCodes.add(94551);
         zipCodes.add(92345);
         zipCodes.add(10600);
     }

    CacheLoader<Integer, List<WeatherData>> loader = new CacheLoader<>() {
        @Override
        public List<WeatherData> load(Integer key) {
                return null;
        }
    };

    LoadingCache<Integer, List<WeatherData>>  cache =  CacheBuilder.newBuilder()
            .expireAfterWrite(30,TimeUnit.MINUTES)
            .build(loader);



    public ResponseEntity<List<WeatherData>> getWeatherData(int zipCode) {
            return fetchWeatherData(zipCode);
    }

    /**
     * create dummy weather data
     * @param zipCode
     * @return ResponseEntity<List<WeatherData>>
     */
    private ResponseEntity<List<WeatherData>> fetchWeatherData(int zipCode) {

        if(!zipCodes.contains(zipCode)) {
            return ResponseEntity.notFound().build();
        }

        List<WeatherData> weatherDataList = cache.getIfPresent(zipCode);

        if (weatherDataList == null) {
            weatherDataList =  generateWeatherData(2.0F);
            cache.put(zipCode, weatherDataList);
            return ResponseEntity.ok().body(weatherDataList);
        } else {
            for (WeatherData weatherData : weatherDataList) {
                weatherData.setCached(true);
            }
            return ResponseEntity.ok().body(weatherDataList);
        }

    }


    private List<WeatherData> generateWeatherData(float maxTemp) {
       List<WeatherData> weatherData = new ArrayList<>();

        for(int i=0;i<=2;i++) {
            WeatherData tempWeatherData = new WeatherData();

            tempWeatherData.setDate(LocalDate.now().plusDays(i));
            tempWeatherData.setMinTemp(generateTemp(2.0F));
            tempWeatherData.setHighTemp(tempWeatherData.getMinTemp()+maxTemp);

            weatherData.add(tempWeatherData);
        }
        return weatherData;
    }

    private float generateTemp(float maxTemp) {
        return (float) (Math.random() * 35 + maxTemp);
    }
}
