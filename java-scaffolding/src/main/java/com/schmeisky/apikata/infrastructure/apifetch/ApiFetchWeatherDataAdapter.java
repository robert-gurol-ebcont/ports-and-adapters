package com.schmeisky.apikata.infrastructure.apifetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schmeisky.apikata.business.FetchWeatherDataPort;
import com.schmeisky.apikata.business.WeatherData;
import com.schmeisky.apikata.config.GlobalConfig;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiFetchWeatherDataAdapter implements FetchWeatherDataPort {

    private final ObjectMapper objectMapper;
    private final ApiWeatherDataMapper modelMapper;
    private final URL urlToReadFrom;

    public ApiFetchWeatherDataAdapter() {
        try {
            this.urlToReadFrom = new URL(GlobalConfig.APP_URL_TO_QUERY);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.objectMapper = new ObjectMapper();
        this.modelMapper = new ApiWeatherDataMapper();
    }

    public ApiFetchWeatherDataAdapter(URL urlToReadFrom) {
        this.urlToReadFrom = urlToReadFrom;
        this.objectMapper = new ObjectMapper();
        this.modelMapper = new ApiWeatherDataMapper();
    }

    @SneakyThrows
    @Override
    public List<WeatherData> getData() {
        try (InputStream inputStream = urlToReadFrom.openStream();
        ) {
            String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            ApiWeatherDataResult rawApiObject = objectMapper.readValue(jsonString, ApiWeatherDataResult.class);
            return rawApiObject.results().stream()
                    .map(entry -> modelMapper.fromApiWeatherData(entry))
                    .toList();
        }
    }
}
