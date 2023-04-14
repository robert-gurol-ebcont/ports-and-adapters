package com.schmeisky.apikata.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private long stationId;
    private String stationName;
    private String date;
    private String time;
    /**
     * Air airTemperature in centigrade
     */
    private String airTemperature;
    /**
     * Atmospheric atmosphericPressure in hPa
     */
    private String atmosphericPressure;

    /**
     * Abbreviated, e.g. "E" or "NNW"
     */
    private String windDirection;
}
