package com.schmeisky.apikata.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ApplicationTest {

    @Test
    public void downloadTheWeather_movesDataFromInputToOutput() {
        List<WeatherData> weatherDataList = aListOfWeatherData();
        FetchWeatherDataPort mockDataFetchPort = mock(FetchWeatherDataPort.class);
        when(mockDataFetchPort.getData()).thenReturn(weatherDataList);
        ExporterPort mockDataExporterPort = mock(ExporterPort.class);
        Application sut = new Application(mockDataFetchPort, mockDataExporterPort);

        sut.downloadTheWeather();

        verify(mockDataExporterPort).exportToNewFile(weatherDataList);
    }

    private List<WeatherData> aListOfWeatherData() {
        WeatherData weatherData1 = new WeatherData(1L, "Reykjavík", "2023-04-14", "09:00:00", "4.1", "1009", "E");
        WeatherData weatherData2 = new WeatherData(422L, "Akureyri", "2023-04-12", "13:37:00", "-42.23", "900", "NNW");
        return List.of(weatherData1, weatherData2);
    }

}