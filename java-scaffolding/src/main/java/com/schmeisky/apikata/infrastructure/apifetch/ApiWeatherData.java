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
    private long stationId;
    @JsonProperty("name")
    private String stationName;
    @JsonProperty("time")
    private String dateTime;
    @JsonProperty("T")
    private String airTemperature;
    @JsonProperty("P")
    private String atmosphericPressure;
    @JsonProperty("D")
    private String windDirection;
}
