package com.schmeisky.apikata.infrastructure.apifetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Documentation of JSON format: see <a href="https://docs.apis.is/#endpoint-weather">here</a>.
 * <p>
 * Very similar but distinct from {@link com.schmeisky.apikata.business.WeatherData} in order to achieve decoupling of the inner app from the adapters layer.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiWeatherData {

    @JsonProperty("id")
    long stationId;
    @JsonProperty("name")
    String stationName;
    @JsonProperty("time")
    String dateTime;
    @JsonProperty("T")
    String airTemperature;
    @JsonProperty("P")
    String atmosphericPressure;
    @JsonProperty("D")
    String windDirection;
}
