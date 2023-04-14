package com.schmeisky.apikata.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    long stationId;
    String stationName;
    String date;
    String time;
    /**
     * Air airTemperature in centigrade
     */
    String airTemperature;
    /**
     * Atmospheric atmosphericPressure in hPa
     */
    String atmosphericPressure;

    /**
     * Abbreviated, e.g. "E" or "NNW"
     */
    String windDirection;
}
