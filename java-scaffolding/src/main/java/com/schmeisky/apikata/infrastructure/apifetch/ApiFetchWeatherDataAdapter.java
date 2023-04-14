package com.schmeisky.apikata.infrastructure.apifetch;

import com.schmeisky.apikata.business.FetchWeatherDataPort;
import com.schmeisky.apikata.business.WeatherData;
import com.schmeisky.apikata.config.GlobalConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ApiFetchWeatherDataAdapter implements FetchWeatherDataPort {

    private final URL urlToReadFrom;

    public ApiFetchWeatherDataAdapter() {
        try {
            this.urlToReadFrom = new URL(GlobalConfig.APP_URL_TO_QUERY);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiFetchWeatherDataAdapter(URL urlToReadFrom) {
        this.urlToReadFrom = urlToReadFrom;
    }

    @Override
    public List<WeatherData> getData() {
        return null;
    }
}
