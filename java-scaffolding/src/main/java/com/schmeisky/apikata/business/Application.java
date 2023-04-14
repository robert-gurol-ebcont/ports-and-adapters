package com.schmeisky.apikata.business;

import java.util.List;

public class Application implements WeatherFetchTriggerPort {
    private final FetchWeatherDataPort dataFetchPort;
    private final ExporterPort exporterPort;

    public Application(FetchWeatherDataPort dataFetchPort, ExporterPort exporterPort) {
        this.dataFetchPort = dataFetchPort;
        this.exporterPort = exporterPort;
    }

    @Override
    public void downloadTheWeather() {
        List<WeatherData> data = dataFetchPort.getData();
        exporterPort.exportToNewFile(data);
    }
}
