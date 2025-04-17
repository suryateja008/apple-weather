package com.suryatejaduggi.weather.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WeatherData
{
    LocalDate date;
    float minTemp;
    float highTemp;
    boolean cached = false;
}
