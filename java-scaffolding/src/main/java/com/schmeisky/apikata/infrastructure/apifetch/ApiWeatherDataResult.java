package com.schmeisky.apikata.infrastructure.apifetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiWeatherDataResult(List<ApiWeatherData> results) {
}
