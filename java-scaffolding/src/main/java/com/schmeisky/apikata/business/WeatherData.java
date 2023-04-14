package com.schmeisky.apikata.business;

public record WeatherData(long stationId,
                          String stationName,
                          String date,
                          String time,
                          /**
                           * Air airTemperature in centigrade
                           */
                          String airTemperature,
                          /**
                           * Atmospheric atmosphericPressure in hPa
                           */
                          String atmosphericPressure,

                          /**
                           * Abbreviated, e.g. "E" or "NNW"
                           */
                          String windDirection) {
}
